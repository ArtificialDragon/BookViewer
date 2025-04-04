package com.dragonlordian.bookreader.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dragonlordian.bookreader.presentation.PdfViewerScreen
import com.dragonlordian.bookreader.presentation.allBooksByCategory.BooksByCategoryScreen
import com.dragonlordian.bookreader.presentation.home.HomeScreen

@Composable
fun NavGraph(
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = Routes.HomeScreen){
        composable<Routes.HomeScreen>{
            HomeScreen(navHostController = navHostController)
        }
        composable<Routes.ShowPdfScreen>{ navBackStackEntry ->  
            val data: Routes.ShowPdfScreen = navBackStackEntry.toRoute()
            PdfViewerScreen(url = data.url)
        }
        composable<Routes.BooksByCategory> { BackStackEntry ->
            val data: Routes.BooksByCategory = BackStackEntry.toRoute()
            BooksByCategoryScreen(category = data.category, navHostController = navHostController)
        }
    }
}