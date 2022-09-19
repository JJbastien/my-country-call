package com.example.countries.api

import com.example.countries.model.CountryResponse
import com.example.countries.utils.COUNTRY_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET


//interface that provides Https functions. We only need the GET function in this case

interface CountriesApiService {
    @GET(COUNTRY_ENDPOINT)
    suspend fun fetchCountries(): Response<List<CountryResponse>>
}


