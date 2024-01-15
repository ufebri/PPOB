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
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.chip.Chip
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.MenuList
import febri.uray.bedboy.core.security.MD5Helper
import febri.uray.bedboy.core.util.SharedPreferencesHelper
import febri.uray.bedboy.core.util.TextHelper
import febri.uray.bedboy.ppob.R
import febri.uray.bedboy.ppob.databinding.ContentHomeFragmentBinding
import febri.uray.bedboy.uicomponent.ads.AdsHelper
import febri.uray.bedboy.uicomponent.chiplayout.addChip

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: ContentHomeFragmentBinding? = null
    private val binding get() = _binding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private var initialLayoutComplete = false
    private lateinit var mAds: AdView

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

            //Init sharedprefences
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

            //init title name
            activity?.title = getString(R.string.app_name)

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

                homeViewModel.postpaidProductList.observe(viewLifecycleOwner) { mData ->
                    when (mData) {
                        is Resource.Loading -> {}
                        is Resource.Success -> {}
                        is Resource.Error -> {}
                    }
                }

                homeViewModel.categoryMenus.forEach {
                    chipMenu.addChip(requireActivity(), it, true)
                }

                chipMenu.setOnCheckedChangeListener { group, checkedId ->
                    val mView = group?.findViewById<Chip>(checkedId)
                    val mTitle = mView?.text.toString()
                    mView?.setOnClickListener {
                        showMenu(mTitle)
                    }
                }

                showMenu(homeViewModel.categoryMenus[0])

                // Initialize the Mobile Ads SDK.
                MobileAds.initialize(requireActivity()) {}
                mAds = AdView(requireActivity())
                adView.addView(mAds)
                adView.viewTreeObserver.addOnGlobalLayoutListener {
                    if (!initialLayoutComplete) {
                        initialLayoutComplete = true
                        loadBanner()
                    }
                }
            }
        }
    }

    private fun loadBanner() {
        mAds.adUnitId = getString(febri.uray.bedboy.core.R.string.admob_banner_id)
        mAds.setAdSize(AdsHelper.calculateAdSize(requireActivity(), mAds))

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device, e.g.,
        // "Use AdRequest.Builder.addTestDevice("ABCDE0123") to get test ads on this device."
        val adRequest = AdRequest.Builder().build()

        // Start loading the ad in the background.
        mAds.loadAd(adRequest)
    }


    private fun showMenu(menuName: String) {
        binding?.apply {
            homeViewModel.menuList(menuName).let { menu ->
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
                tvSelectedTitle.text = menuName
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
                    menuList
                )
            findNavController().navigate(action)
        }
    }
}