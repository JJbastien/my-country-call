package com.example.countries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.countries.R
import com.example.countries.databinding.FragmentCountryBinding
import com.example.countries.model.CountryResponse
import com.example.countries.utils.CountryAdapter
import com.example.countries.utils.UIState

// Fragment to display the SAT scores
class CountryFragment : ViewModelFragment() {

    private var _binding: FragmentCountryBinding? = null
    private val binding: FragmentCountryBinding get() = _binding!!

    private lateinit var countryAdapter: CountryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCountryBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }
    //  fragment for school list using liveData to observe life cycle and use State to
    // update UI
    private fun configureObserver() {
        viewModel.countryLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Success<*> -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        loadingError.visibility = View.GONE
                        countryAdapter = CountryAdapter(setCountries =::setCountry)
                        countryAdapter.setCountryList(state.response as List<CountryResponse>)
                        rvCountryList.adapter = countryAdapter
                    }
                }
                is UIState.Error -> {
                    binding.apply {
                        pbLoading.visibility = View.GONE
                        loadingError.text = state.exception.message
                    }
                }
                else -> {}
            }
        }
    }
    //function to set data received to fragment attached to the activity containing the fragment in question
    private fun setCountry(countries: CountryResponse) {
        viewModel.setCountry(countries)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CountryFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}