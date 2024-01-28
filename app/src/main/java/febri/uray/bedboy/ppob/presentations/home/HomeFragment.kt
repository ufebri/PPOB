package febri.uray.bedboy.ppob.presentations.home

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ContentHomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            //init title name
            activity?.title = getString(R.string.app_name)

            binding?.apply {

                //load balance
                loadBalance()

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
                    chipMenu.addChip(
                        requireActivity(), it, it.contains("topup", true)
                    )
                }

                chipMenu.setOnCheckedChangeListener { group, checkedId ->
                    val mView = group?.findViewById<Chip>(checkedId)
                    val mTitle = mView?.text.toString()
                    mView?.apply {
                        setOnClickListener { showMenuByCategory(mTitle) }

                        // Reset the background color for all chips
                        for (i in 0 until group.childCount) {
                            val child = group.getChildAt(i) as Chip
                            if (child.id == checkedId) {
                                // Set the background color for the selected chip
                                child.chipBackgroundColor = ContextCompat.getColorStateList(
                                    context, febri.uray.bedboy.uicomponent.R.color.colorPrimary
                                )
                                child.setTextColor(Color.WHITE)
                            } else {
                                // Reset the background color for other chips
                                child.chipBackgroundColor = ContextCompat.getColorStateList(
                                    context, febri.uray.bedboy.uicomponent.R.color.colorGrey
                                )
                                child.setTextColor(Color.BLACK)
                            }
                        }
                    }
                }

                showMenuByCategory(homeViewModel.categoryMenus[0])

                //Set Search Menu
                etSearchMenu.doAfterTextChanged { text: Editable? ->
                    if (!text.isNullOrEmpty() && text.length > 1) {
                        //Doing Search
                        showMenuBySearch(text.toString())
                    } else {
                        //Back to First Menu
                        showMenuByCategory(homeViewModel.categoryMenus[0])
                    }
                }


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

                srlHome.setOnRefreshListener {
                    loadBalance()
                    srlHome.isRefreshing = false
                }
            }
        }
    }

    private fun loadBalance() {
        //Init sharedprefences
        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())
        val username = sharedPreferencesHelper.getString("username")
        val key = sharedPreferencesHelper.getString("apikey")

        val sign = MD5Helper.calculateMD5(
            String.format(
                "%s%sbl", username, key
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
                        tvValueBalance.text = TextHelper.formatRupiah(mData.data?.balance ?: "")
                        showBalance(true)
                    }

                    is Resource.Error -> {
                        tvValueBalance.text =
                            String.format("Something error: %s", mData.message)
                        showBalance(true)
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


    private fun showMenuByCategory(menuCategory: String) {
        binding?.apply {
            homeViewModel.menuCategoryList(menuCategory).let { menu ->
                rvMenu.apply {
                    layoutManager = GridLayoutManager(
                        requireActivity(), 4, GridLayoutManager.VERTICAL, false
                    )

                    val menuAdapter = MenuAdapter { goToMenu(it) }
                    adapter = menuAdapter
                    menuAdapter.submitList(menu)
                }
                tvSelectedTitle.apply {
                    text = menuCategory
                    isVisible = true
                }
            }
        }
    }

    private fun showMenuBySearch(searchMenu: String) {
        binding?.apply {
            homeViewModel.menuSearchList(searchMenu).let { menu ->
                rvMenu.apply {
                    layoutManager = GridLayoutManager(
                        requireActivity(), 4, GridLayoutManager.VERTICAL, false
                    )

                    val menuAdapter = MenuAdapter { goToMenu(it) }
                    adapter = menuAdapter
                    menuAdapter.submitList(menu)
                }
                tvSelectedTitle.isGone = true
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
            val action = HomeFragmentDirections.actionHomeToPrepaidFragment(
                it.toTypedArray(), menuList
            )
            findNavController().navigate(action)
        }
    }
}