package marsan_almanecado.e8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import marsan_almanecado.e8.viewmodel.AuthViewModel
import marsan_almanecado.e8.viewmodel.ProductosViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authVM: AuthViewModel = viewModel()
    val productosVM: ProductosViewModel = viewModel()

    // Si usaste Opción 1:
    AppNavHost(  // <-- Cambiado para coincidir con el nuevo nombre
        navController = navController,
        authVM = authVM,
        productosVM = productosVM
    )

    // O si usaste Opción 2 (dejar como está):
    // NavHost(
    //     navController = navController,
    //     authVM = authVM,
    //     productosVM = productosVM
    // )
}