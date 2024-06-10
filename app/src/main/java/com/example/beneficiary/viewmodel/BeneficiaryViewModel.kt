package com.example.beneficiary.viewmodel


import BeneficiaryRepository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beneficiary.model.Beneficiary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeneficiaryViewModel(private val repository: BeneficiaryRepository) : ViewModel() {
    private val _beneficiaries = MutableLiveData<List<Beneficiary>>()
    val beneficiaries: LiveData<List<Beneficiary>> get() = _beneficiaries
    private val _selectedBeneficiary = MutableLiveData<Beneficiary?>()
    val selectedBeneficiary: LiveData<Beneficiary?> get() = _selectedBeneficiary

   /* init {
        _beneficiaries.value = mutableListOf()  // Initialize with an empty list
    }
*/

    fun loadBeneficiaries(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            val loadedBeneficiaries = repository.loadBeneficiaries().toMutableList()
            _beneficiaries.postValue(loadedBeneficiaries)
        }
    }

    fun selectedBeneficiary(beneficiary: Beneficiary?) {
        _selectedBeneficiary.value = beneficiary
    }
}