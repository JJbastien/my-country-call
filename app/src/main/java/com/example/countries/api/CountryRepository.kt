package com.example.countries.api

import com.example.countries.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


//Repository layer interface

interface CountryRepository {
    suspend fun fetchCountries(): Flow<UIState>

}

//Repository layer implementation with dependency injection from apiService

class CountryRepositoryImpl @Inject constructor(
    private val service: CountriesApiService
): CountryRepository {
    override suspend fun fetchCountries(): Flow<UIState> =
        flow {
            emit(UIState.Loading)
            try {
                val response = service.fetchCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.Success(it))
                    } ?: throw Exception("Response is null")
                }
                else throw Exception("Failed country network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }
}






