package febri.uray.bedboy.prepaid.form

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.prepaid.databinding.ContentPrepaidFragmentBinding

@AndroidEntryPoint
class PrepaidFragment : Fragment() {

    private var _binding: ContentPrepaidFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel: PrepaidViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentPrepaidFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.apply {

                etProduct.setAdapter(
                    ArrayAdapter(
                        requireActivity(),
                        android.R.layout.simple_list_item_1,
                        viewModel.providerList
                    )
                )

                etCustomerNumber.doAfterTextChanged { text: Editable? ->
                    if (!text.isNullOrEmpty() && !etProduct.text.isNullOrEmpty()) {
                        viewModel.packageList(etProduct.text.toString())
                            .observe(viewLifecycleOwner) { productLists ->
                                if (!productLists.isNullOrEmpty()) {
                                    rvDenom.apply {
                                        val mAdapter = ProductAdapter {
                                            goToPayment(
                                                it.productCode,
                                                etCustomerNumber.text.toString()
                                            )
                                        }
                                        adapter = mAdapter
                                        layoutManager = LinearLayoutManager(requireActivity())
                                        mAdapter.submitList(productLists)

                                        isVisible = true
                                    }
                                } else {
                                    rvDenom.isGone = true
                                }
                            }
                    } else {
                        rvDenom.isGone = true
                    }
                }
            }
        }
    }


    private fun goToPayment(mSelectedItemCode: String, mCustomerNumber: String) {
        // Use the NavController to navigate with arguments
        val action = PrepaidFragmentDirections.actionPrepaidToTopup(
            prodCode = mSelectedItemCode,
            customerID = mCustomerNumber
        )
        findNavController().navigate(action)
    }
}