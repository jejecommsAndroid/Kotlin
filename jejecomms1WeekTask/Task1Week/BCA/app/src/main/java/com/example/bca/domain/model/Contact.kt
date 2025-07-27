package com.example.bca.domain.model.contact
data class Contact(
    val id: Int, // <-- Add this
    val name: String,
    val company: String,
    val phone: String,
    val email: String,
    val notes: String,
    val colorHex: String,
    val isFavorite: Boolean = false,
) {
    val initials: String
        get() = name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
}
