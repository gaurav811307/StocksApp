package com.example.stock.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.stock.ui.explore.ExploreScreen
import com.example.stock.ui.product.ProductScreen
//import com.example.stock.ui.search.SearchScreen
import com.example.stock.ui.viewall.ViewAllScreen

sealed class Screen(val route: String) {
    object Explore : Screen("explore")
    object Search : Screen("search")
    object ViewAll : Screen("view_all/{type}") {
        fun withArgs(type: String) = "view_all/$type"
    }
    object Product : Screen("product/{symbol}") {
        fun withArgs(symbol: String) = "product/$symbol"
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Explore.route,
        modifier = modifier
    ) {

        composable(Screen.Explore.route) {
            ExploreScreen(
                onStockClick = { symbol ->
                    navController.navigate(Screen.Product.withArgs(symbol))
                },
                onViewAllClick = { type ->
                    navController.navigate(Screen.ViewAll.withArgs(type))
                }
            )
        }

        composable(Screen.Search.route) {
//            SearchScreen(
//                onStockClick = { symbol ->
//                    navController.navigate(Screen.Product.withArgs(symbol))
//                }
//            )
        }

        composable(
            route = Screen.ViewAll.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: "gainers"
            ViewAllScreen(
                type = type,
                onStockClick = { symbol ->
                    navController.navigate(Screen.Product.withArgs(symbol))
                }
            )
        }

        composable(
            route = Screen.Product.route,
            arguments = listOf(navArgument("symbol") { type = NavType.StringType })
        ) { backStackEntry ->
            val symbol = backStackEntry.arguments?.getString("symbol") ?: ""
            ProductScreen(symbol = symbol)
        }
    }
}
