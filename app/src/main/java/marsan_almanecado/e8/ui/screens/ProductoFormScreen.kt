package marsan_almanecado.e8.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marsan_almanecadoe8.model.Producto
import marsan_almanecado.e8.viewmodel.ProductosViewModel  // <-- IMPORTACIÓN CORRECTA
import java.util.UUID

@Composable
fun ProductoFormScreen(
    viewModel: ProductosViewModel,  // <-- CORRECCIÓN: sin comillas ni .kt
    navController: NavController,
    producto: Producto? = null
) {
    var nombre by remember { mutableStateOf(producto?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(producto?.descripcion ?: "") }
    var precio by remember { mutableStateOf(producto?.precio?.toString() ?: "") }
    var cantidad by remember { mutableStateOf(producto?.cantidad?.toString() ?: "") }
    var departamentoId by remember { mutableStateOf(producto?.departamentoId ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = if (producto == null) "Nuevo Producto" else "Editar Producto",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = departamentoId,
            onValueChange = { departamentoId = it },
            label = { Text("ID Departamento") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {
                    val nuevoProducto = Producto(
                        id = producto?.id ?: UUID.randomUUID().toString(),
                        nombre = nombre,
                        descripcion = descripcion,
                        precio = precio.toDoubleOrNull() ?: 0.0,
                        cantidad = cantidad.toIntOrNull() ?: 0,
                        departamentoId = departamentoId
                    )

                    if (producto == null) {
                        viewModel.agregarProducto(nuevoProducto)
                    } else {
                        viewModel.actualizarProducto(nuevoProducto)
                    }

                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f),
                enabled = nombre.isNotBlank() && precio.isNotBlank()
            ) {
                Text(if (producto == null) "Guardar" else "Actualizar")
            }
        }
    }
}