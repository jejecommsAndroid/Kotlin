package com.example.bca.presentation.navigation
import com.example.bca.R
import androidx.annotation.DrawableRes

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Contacts : BottomNavItem("contacts", "Contacts", R.drawable.contacts_24)
    object Cards : BottomNavItem("cards", "Cards", R.drawable.card_24)
    object Scan : BottomNavItem("scan", "Scan", R.drawable.scanner_24)
    object Profile : BottomNavItem("profile", "Profile", R.drawable.profile_24)
}


