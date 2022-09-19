package com.example.countries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countries.api.CountryRepository
import com.example.countries.model.CountryResponse
import com.example.countries.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

//viewModel setup to serve as a bridge between data layer and views
//we use dependency injection to inject the repository
@HiltViewModel
class CountryViewModel @Inject constructor(
    private val repository: CountryRepository
    ): ViewModel() {

    private val _countryLiveData: MutableLiveData<UIState> = MutableLiveData()
    val countryLiveData: LiveData<UIState> get() = _countryLiveData


    var currentCountry: CountryResponse? = null

    init {
        fetchCountries()
    }

    //Coroutine set up to make call to api to get schools without blocking the main thread
    private fun fetchCountries() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchCountries().collect { state ->
                _countryLiveData.postValue(state)
            }
        }
    }
    fun setCountry(country: CountryResponse?) {
        currentCountry = country

    }
}



