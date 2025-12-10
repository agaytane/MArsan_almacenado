package marsan_almanecado.e8

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
// Solo estas importaciones
import marsan_almanecado.e8.ui.screens.DashboardScreen
import marsan_almanecado.e8.ui.screens.DepartamentoFormScreen
import marsan_almanecado.e8.ui.screens.LoginScreen
import marsan_almanecado.e8.ui.screens.ProductoFormScreen
import marsan_almanecado.e8.ui.screens.ProductosPantalla  // <-- SOLO ESTA
import marsan_almanecado.e8.viewmodel.AuthViewModel
import marsan_almanecado.e8.viewmodel.ProductosViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    authVM: AuthViewModel,
    productosVM: ProductosViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                navController = navController,
                authViewModel = authVM
            )
        }

        composable(Routes.DASHBOARD) {
            DashboardScreen(
                navController = navController,
                authViewModel = authVM
            )
        }

        composable(Routes.PRODUCTO_FORM) { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")
            val productos by productosVM.productos.collectAsState()
            val producto = productoId?.let { id ->
                productos.find { it.id == id }
            }

            ProductoFormScreen(
                viewModel = productosVM,
                navController = navController,
                producto = producto
            )
        }

        composable(Routes.PRODUCTOS) {
            // Usar la función importada explícitamente
            ProductosPantalla(
                viewModel = productosVM,
                navController = navController
            )
        }

        composable(Routes.DEPARTAMENTO_FORM) {
            DepartamentoFormScreen(navController = navController)
        }
    }
}

object Routes {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val PRODUCTOS = "productos"
    const val PRODUCTO_FORM = "producto_form/{productoId}"
    const val DEPARTAMENTO_FORM = "departamento_form"

    fun productoForm(productoId: String? = null): String {
        return if (productoId != null) {
            "producto_form/$productoId"
        } else {
            "producto_form"
        }
    }
}