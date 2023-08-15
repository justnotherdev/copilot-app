package com.justanotherdev.copilot_app.domain.model

internal data class Motorcycle(
    val id: String,
    val codename: String = "",
    val status: String = "",
    val brand: String = "",
    val imagePath: String = "",
)
