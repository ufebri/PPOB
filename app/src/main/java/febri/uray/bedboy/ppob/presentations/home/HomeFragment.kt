package febri.uray.bedboy.ppob.presentations.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import febri.uray.bedboy.ppob.R
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
                        is Resource.Loading -> {}
                        is Resource.Success -> tvValueBalance.text =
                            TextHelper.formatRupiah(mData.data?.balance ?: "")

                        is Resource.Error -> tvValueBalance.text =
                            String.format("Something error: %s", mData.message)
                    }
                }

                homeViewModel.priceList.observe(viewLifecycleOwner) { mData ->
                    when (mData) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {}
                        is Resource.Error -> {}
                    }
                }

                homeViewModel.menuList.observe(viewLifecycleOwner) { menu ->
                    if (menu != null) {
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
    }

    private fun goToMenu(menuList: MenuList) {
        when (menuList.idMenu) {
            "bicara" -> findNavController().navigate(R.id.action_home_to_callPackage)
            else -> Toast.makeText(requireActivity(), menuList.nameMenu, Toast.LENGTH_LONG).show()
        }
    }
}