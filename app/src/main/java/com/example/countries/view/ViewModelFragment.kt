package com.example.countries.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.countries.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class ViewModelFragment: Fragment() {
    protected val viewModel: CountryViewModel by activityViewModels()
}