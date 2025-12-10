package marsan_almanecado.e8.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import marsan_almanecado.e8.Routes
import marsan_almanecado.e8.viewmodel.AuthViewModel  // <-- IMPORTACIÓN NECESARIA

@Composable
fun DashboardScreen(
    navController: NavController,
    authViewModel: AuthViewModel  // <-- CORRECCIÓN: sin comillas ni .kt
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = { navController.navigate(Routes.PRODUCTOS) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Productos")
        }

        Button(
            onClick = { navController.navigate(Routes.productoForm()) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Producto")
        }

        Button(
            onClick = { navController.navigate(Routes.DEPARTAMENTO_FORM) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Departamento")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                authViewModel.logout()
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.DASHBOARD) { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer
            )
        ) {
            Text("Cerrar Sesión")
        }
    }
}