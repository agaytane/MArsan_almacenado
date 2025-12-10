// ProductosViewModel.kt
package marsan_almanecado.e8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsan_almanecadoe8.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductosViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos.asStateFlow()

    private val _selectedProducto = MutableStateFlow<Producto?>(null)
    val selectedProducto: StateFlow<Producto?> = _selectedProducto.asStateFlow()

    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            _productos.value = _productos.value + producto
        }
    }

    fun actualizarProducto(productoActualizado: Producto) {
        viewModelScope.launch {
            _productos.value = _productos.value.map { producto ->
                if (producto.id == productoActualizado.id) productoActualizado else producto
            }
        }
    }

    fun eliminarProducto(id: String) {
        viewModelScope.launch {
            _productos.value = _productos.value.filter { it.id != id }
        }
    }

    fun setSelectedProducto(producto: Producto?) {
        _selectedProducto.value = producto
    }

    fun getProductoById(id: String): Producto? {
        return _productos.value.find { it.id == id }
    }
}