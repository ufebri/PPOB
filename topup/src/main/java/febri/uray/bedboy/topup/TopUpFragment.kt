package febri.uray.bedboy.topup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.domain.model.ResultTransaction
import febri.uray.bedboy.topup.databinding.ContentTopupFragmentBinding

@AndroidEntryPoint
class TopUpFragment : Fragment() {

    private var _binding: ContentTopupFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel: TopUpViewModel by viewModels()
    private val args: TopUpFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentTopupFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val bundleProductCode = args.prodCode
            val bundleCustomerID = args.customerID

            if (!bundleProductCode.isNullOrEmpty() && !bundleCustomerID.isNullOrEmpty()) {
                viewModel.doTopUp(productCode = bundleProductCode, customerID = bundleCustomerID)
                    .observe(viewLifecycleOwner) { mResult ->
                        when (mResult) {
                            is Resource.Loading -> {}
                            is Resource.Success -> {
                                if (mResult.data != null)
                                    showViewData(mResult.data)
                            }

                            is Resource.Error -> {}
                        }
                    }
            } else {
                Toast.makeText(requireActivity(), "Something Error", Toast.LENGTH_LONG).show()
            }
        }
    }


    private fun showViewData(mData: ResultTransaction?) {
        mData?.let {
            binding?.apply {
                tvStatus.text = it.statusTransaction.title
                animationView.setAnimationFromUrl(it.statusTransaction.value)

                rvPayment.apply {
                    val mAdapter = TopUpAdapter { doUpdateStatus() }
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = mAdapter

                    mAdapter.submitList(it.listTransaction)
                }
            }
        }
    }

    private fun doUpdateStatus() {

    }
}