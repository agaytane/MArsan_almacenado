package com.example.marsan_almanecadoe8.model

data class Producto(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,
    val departamentoId: String,
    val imagenUrl: String? = null
)