package febri.uray.bedboy.ppob.presentations.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.security.MD5Helper
import febri.uray.bedboy.core.util.SharedPreferencesHelper
import febri.uray.bedboy.core.util.TextHelper
import febri.uray.bedboy.ppob.databinding.ContentHomeFragmentBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: ContentHomeFragmentBinding? = null
    private val binding get() = _binding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentHomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
            val username = sharedPreferencesHelper.getString("username")
            val key = sharedPreferencesHelper.getString("apikey")

            val sign = MD5Helper.calculateMD5(
                String.format(
                    "%s%sbl",
                    username,
                    key
                )
            )

            val request: JsonObject = JsonObject().apply {
                addProperty("username", username)
                addProperty("sign", sign)
            }

            binding?.apply {
                homeViewModel.balance(request).observe(viewLifecycleOwner) { mData ->
                    when (mData) {
                        is Resource.Loading -> showBalance(false)
                        is Resource.Success -> {
                            tvValueBalance.text =
                                TextHelper.formatRupiah(mData.data?.balance ?: "")
                            showBalance(true)
                        }

                        is Resource.Error -> {
                            tvValueBalance.text =
                                String.format("Something error: %s", mData.message)
                            showBalance(true)
                        }
                    }
                }

                homeViewModel.priceList.observe(viewLifecycleOwner) { mData ->
                    when (mData) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {}
                        is Resource.Error -> {}
                    }
                }

                homeViewModel.menuList.let { menu ->
                    rvMenu.apply {
                        layoutManager = GridLayoutManager(
                            requireActivity(),
                            4,
                            GridLayoutManager.VERTICAL,
                            false
                        )

                        val menuAdapter = MenuAdapter { goToMenu(it) }
                        adapter = menuAdapter
                        menuAdapter.submitList(menu)
                    }
                }
            }
        }
    }

    private fun showBalance(state: Boolean) {
        binding?.apply {
            state.let {
                pbBalance.isGone = state
                tvValueBalance.isVisible = state
            }
        }
    }

    private fun goToMenu(menuList: MenuList) {
        homeViewModel.productCategoryList(menuList.idMenu).observe(viewLifecycleOwner) {
            val action =
                HomeFragmentDirections.actionHomeToPrepaidFragment(
                    it.toTypedArray(),
                    menuList.idMenu
                )
            findNavController().navigate(action)
        }
    }
}