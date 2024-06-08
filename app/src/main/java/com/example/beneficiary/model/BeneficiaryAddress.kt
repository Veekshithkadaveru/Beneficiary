package com.example.beneficiary.model

data class BeneficiaryAddress(
    val firstLineMailing: String,
    val scndLineMailing: String,
    val city: String,
    val zipCode: String,
    val stateCode: String,
    val country: String
)
