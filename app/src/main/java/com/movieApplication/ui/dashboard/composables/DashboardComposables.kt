package com.movieApplication.ui.dashboard.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.movieApplication.ui.dashboard.navhost.DashboardNavHost
import com.movieApplication.ui.theme.MovieApplicationTheme

@Composable
fun DashboardScreen() {
    DashboardContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent() {
    val navController = rememberNavController()
    Scaffold(
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                DashboardNavHost(navController = navController)
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
fun DashboardScreenPreview() {
    MovieApplicationTheme {
        DashboardContent()
    }
}