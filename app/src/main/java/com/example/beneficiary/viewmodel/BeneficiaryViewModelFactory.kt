package com.example.beneficiary.viewmodel

import BeneficiaryRepository
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BeneficiaryViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeneficiaryViewModel::class.java)) {
            val repository = BeneficiaryRepository(context)
            @Suppress("UNCHECKED_CAST")
            return BeneficiaryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}