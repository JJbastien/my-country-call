package com.example.countries.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.databinding.ActivityMainBinding.inflate
import com.example.countries.databinding.CountryItemBinding
import com.example.countries.model.CountryResponse

//Adapter set up for Recycler view
class CountryAdapter(
    private val countries: MutableList<CountryResponse> = mutableListOf(),
    private val setCountries: (CountryResponse) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(
        private val binding: CountryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
            fun bind(country: CountryResponse) {
                binding.apply {
                    name.text = country.name
                    language.text = country.capital


                }
            }
    }
    //function to get new data from the api
    //We clear the data and then we all the new data
    fun setCountryList(newList: List<CountryResponse>) {
        countries.clear()
        countries.addAll(newList)
        notifyItemRangeChanged(0, itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size
}


