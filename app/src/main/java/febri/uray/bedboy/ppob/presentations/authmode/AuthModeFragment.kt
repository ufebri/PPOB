package febri.uray.bedboy.ppob.presentations.authmode

import android.os.Bundle
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.BuildConfig
import febri.uray.bedboy.core.data.Resource
import febri.uray.bedboy.core.security.MD5Helper
import febri.uray.bedboy.ppob.BalanceViewModel
import febri.uray.bedboy.ppob.databinding.ContentAuthmodeFragmentBinding
import febri.uray.bedboy.util.local.SharedPreferencesHelper

@AndroidEntryPoint
class AuthModeFragment : Fragment() {

    private var _binding: ContentAuthmodeFragmentBinding? = null
    private val binding get() = _binding
    private val balanceViewModel: BalanceViewModel by viewModels()
    private var selectedOption: String? = null
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    interface OnItemSelectedListener {
        fun onItemSelected(selectedOption: String)
    }

    private var onItemSelectedListener: OnItemSelectedListener? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = ContentAuthmodeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init view
        showListServices()
        showLoadingView(false)
        sharedPreferencesHelper = SharedPreferencesHelper(requireActivity())

        if (activity != null) {
            binding?.apply {
                var isValid: Boolean

                etServices.setText(if (BuildConfig.DEBUG) BuildConfig.APIKEY else "")
                etUsername.setText(if (BuildConfig.DEBUG) "" else "")

                etServices.doAfterTextChanged { text: Editable? ->
                    isValid = text.toString().length > 1
                    checkData(isValid)
                }
                etUsername.doAfterTextChanged { text: Editable? ->
                    isValid = text.toString().length > 1
                    checkData(isValid)
                }

            }
        }
    }

    private fun checkData(isValid: Boolean) {
        binding?.apply {
            val sign = MD5Helper.calculateMD5(
                String.format(
                    "%s%sbl",
                    etUsername.text.toString(),
                    etServices.text.toString()
                )
            )

            btnSubmit.isEnabled = isValid
            btnSubmit.setOnClickListener {
                val request: JsonObject = JsonObject().apply {
                    addProperty("username", etUsername.text.toString())
                    addProperty("sign", sign)
                }
                fetchData(request)
            }
        }
    }

    private fun showLoadingView(state: Boolean) {
        binding?.apply {
            progressbar.isVisible = state
            etUsername.isEnabled = !state
            etServices.isEnabled = !state
        }
    }

    private fun fetchData(request: JsonObject) {
        balanceViewModel.balance(request).observe(viewLifecycleOwner) { mData ->
            binding?.apply {
                when (mData) {
                    is Resource.Loading -> showLoadingView(true)
                    is Resource.Success -> if (mData.data != null) {
                        Toast.makeText(requireActivity(), mData.data?.balance, Toast.LENGTH_LONG)
                            .show()
                        sharedPreferencesHelper.saveString("username", etUsername.text.toString())
                        sharedPreferencesHelper.saveString("apikey", etServices.text.toString())
                        showLoadingView(false)
                    } else {
                        Toast.makeText(requireActivity(), mData.message, Toast.LENGTH_LONG).show()
                        showLoadingView(false)
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireActivity(), mData.message, Toast.LENGTH_LONG).show()
                        showLoadingView(false)
                    }
                }
            }
        }
    }

    private fun showListServices() {
        balanceViewModel.services.forEach { mValue ->
            mValue.let {
                val radiobutton = RadioButton(requireActivity())
                radiobutton.id = it.hashCode()
                radiobutton.text = it

                // Set layout gravity to start
                radiobutton.layoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.START
                }

                binding?.rgServices?.addView(radiobutton)

                // Add a click listener to handle state changes
                radiobutton.setOnClickListener {
                    onRadioButtonClicked(mValue)
                }
            }
        }
    }

    private fun onRadioButtonClicked(option: String) {
        // Execute logic before changing the state
        // For example, you can prevent changing the state based on certain conditions
        if (someCondition()) {
            // Prevent changing the state
            return
        }

        // Update the selected option
        selectedOption = option

        // Update the UI or perform additional actions
        // ...
        binding?.apply {
            tilServices.isVisible = true
            tilUsername.isVisible = true
            btnSubmit.isVisible = true

            onItemSelectedListener?.onItemSelected(option)
        }

        // Manually update the checked state of the radio button
        updateRadioButtonState(option)
    }

    private fun someCondition(): Boolean {
        // Add your condition logic here
        return false
    }

    private fun updateRadioButtonState(option: String) {
        // Manually update the checked state of the radio button
        binding?.apply {
            for (i in 0 until rgServices.childCount) {
                val radioButton = rgServices.getChildAt(i) as? RadioButton
                if (radioButton?.id == option.hashCode()) {
                    radioButton.isChecked = true
                } else {
                    radioButton?.isChecked = false
                }
            }
        }
    }
}