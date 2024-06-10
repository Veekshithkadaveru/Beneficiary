package com.example.beneficiary.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beneficiary.viewmodel.BeneficiaryViewModel

@Composable
fun NavigationComponent(viewModel: BeneficiaryViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "beneficiaryList") {
        composable("beneficiaryList") {
            BeneficiaryListScreen(navController, viewModel)
        }
        composable(
            "beneficiaryDetails/{beneficiaryId}",
            arguments = listOf(navArgument("beneficiaryId") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            val beneficiaryId = navBackStackEntry.arguments?.getInt("beneficiaryId")
            val beneficiary = viewModel.beneficiaries.value?.get(beneficiaryId ?: 0)
            beneficiary?.let {
                BeneficiaryDetails(beneficiary = it, onBackClick = { navController.popBackStack() })

            }
        }
    }
}