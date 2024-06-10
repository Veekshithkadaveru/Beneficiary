package com.example.beneficiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.beneficiary.composables.NavigationComponent
import com.example.beneficiary.ui.theme.BeneficiaryTheme
import com.example.beneficiary.viewmodel.BeneficiaryViewModel
import com.example.beneficiary.viewmodel.BeneficiaryViewModelFactory

class MainActivity : ComponentActivity() {
    private val beneficiaryViewModel: BeneficiaryViewModel by viewModels {
        BeneficiaryViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeneficiaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComponent(beneficiaryViewModel)
                }
            }
        }
    }
}

