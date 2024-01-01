package febri.uray.bedboy.ppob.presentations.home.bicara

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
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.domain.model.ProductList
import febri.uray.bedboy.ppob.databinding.ContentBicaraFragmentBinding

@AndroidEntryPoint
class BicaraFragment : Fragment() {

    private var _binding: ContentBicaraFragmentBinding? = null
    private val binding get() = _binding
    private val viewModel: BicaraViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContentBicaraFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.apply {

                etProvider.setAdapter(
                    ArrayAdapter(
                        requireActivity(),
                        android.R.layout.simple_list_item_1,
                        viewModel.providerList
                    )
                )

                etPhoneNumber.doAfterTextChanged { text: Editable? ->
                    if (!text.isNullOrEmpty() && !etProvider.text.isNullOrEmpty()) {
                        viewModel.packageList(etProvider.text.toString())
                            .observe(viewLifecycleOwner) {
                                if (!it.isNullOrEmpty()) {
                                    rvDenom.apply {
                                        val mAdapter = PackageAdapter { goToPayment(it) }
                                        adapter = mAdapter
                                        layoutManager = LinearLayoutManager(requireActivity())
                                        mAdapter.submitList(it)

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


    private fun goToPayment(mDataSelected: ProductList) {

    }
}