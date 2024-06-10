package com.example.beneficiary.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.beneficiary.model.Beneficiary
import com.example.beneficiary.viewmodel.BeneficiaryViewModel


@Composable
fun BeneficiaryListScreen(navController: NavHostController, viewModel: BeneficiaryViewModel) {
    val context = LocalContext.current
    val beneficiaries by viewModel.beneficiaries.observeAsState(emptyList())


    LaunchedEffect(Unit) {
        viewModel.loadBeneficiaries(context)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        BeneficiaryList(beneficiaries = beneficiaries, onBeneficiaryClick = { beneficiary ->
            val beneficiaryId = beneficiaries.indexOf(beneficiary)
            navController.navigate("beneficiaryDetails/$beneficiaryId")
        })
    }
}


@Composable
fun BeneficiaryList(beneficiaries: List<Beneficiary>, onBeneficiaryClick: (Beneficiary) -> Unit) {
    Column {
        beneficiaries.forEach { beneficiary ->
            BeneficiaryItem(
                beneficiary = beneficiary,
                onClick = { onBeneficiaryClick(beneficiary) })
        }
    }
}


@Composable
fun BeneficiaryItem(beneficiary: Beneficiary, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onClick() },
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${beneficiary.firstName} ${beneficiary.lastName}", fontSize = 20.sp)
                Text(text = beneficiary.beneType, fontSize = 16.sp)
                Text(
                    text = when (beneficiary.designationCode) {
                        "P" -> "Primary"
                        "C" -> "Contingent"
                        else -> "Unknown"
                    },
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun BeneficiaryDetails(beneficiary: Beneficiary, onBackClick: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = onBackClick, modifier = Modifier.padding(bottom = 16.dp)) {
            Text(text = "Back")
        }
        Text(text = "Name: ${beneficiary.firstName} ${beneficiary.lastName}", fontSize = 20.sp)
        Text(text = "SSN: ${beneficiary.socialSecurityNumber}", fontSize = 20.sp)
        Text(text = "Date of Birth: ${beneficiary.dateOfBirth}", fontSize = 20.sp)
        Text(text = "Phone: ${beneficiary.phoneNumber}", fontSize = 20.sp)
        Text(
            text = "Address: ${beneficiary.beneficiaryAddress.firstLineMailing}, " +
                    "${beneficiary.beneficiaryAddress.city}, ${beneficiary.beneficiaryAddress.stateCode} " +
                    "${beneficiary.beneficiaryAddress.zipCode}, ${beneficiary.beneficiaryAddress.country}",
            fontSize = 20.sp
        )
    }
}
