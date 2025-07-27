// ✅ Updated: MainScreen.kt
package com.example.bca.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bca.domain.model.sampleContacts
import com.example.bca.presentation.cards.CardsScreen
import com.example.bca.presentation.contact.ContactDetailScreen
import com.example.bca.presentation.contact.ContactsScreen
import com.example.bca.presentation.contacts.EditContactScreen
import com.example.bca.presentation.profile.ProfileScreen
import com.example.bca.presentation.scan.ScanScreen

@Composable
fun MainScreen(navController1: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(
                    Screen.Contacts.route,
                    Screen.Cards.route,
                    Screen.Scan.route,
                    Screen.Profile.route
                )
            ) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onItemClick = { route ->
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Contacts.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Contacts.route) {
                ContactsScreen(
                    onCardClick = { contact ->
                        navController.navigate("contact_detail/${contact.name}")
                    }
                )
            }
            composable(Screen.Cards.route) { CardsScreen() }
            composable(Screen.Scan.route) { ScanScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
            composable("contact_detail/{contactName}") { backStackEntry ->
                val contactName = backStackEntry.arguments?.getString("contactName")
                val contact = sampleContacts.find { it.name == contactName }

                contact?.let {
                    ContactDetailScreen(
                        contact = it,
                        onEditClick = { /* Handle edit */ },
                        onBackClick = { navController.popBackStack() } // ✅ Added
                    )
                }

            }
            composable("edit_contact/{contactName}") { backStackEntry ->
                val contactName = backStackEntry.arguments?.getString("contactName")
                val contact = sampleContacts.find { it.name == contactName }

                contact?.let {
                    EditContactScreen(
                        contact = it,
                        onSave = { updated ->
                            // Save logic here (e.g. update local list or DB)
                            navController.popBackStack() // Go back to details after save
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    val navItems = listOf(
        Triple(Screen.Contacts, Icons.Default.ContactPage, "Contacts"),
        Triple(Screen.Cards, Icons.Default.CreditCard, "Cards"),
        Triple(Screen.Scan, Icons.Default.QrCodeScanner, "Scan"),
        Triple(Screen.Profile, Icons.Default.AccountBox, "Profile"),
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        navItems.forEach { (screen, icon, label) ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = { onItemClick(screen.route) },
                icon = { Icon(imageVector = icon, contentDescription = label) },
                label = { Text(label) },
                alwaysShowLabel = false
            )
        }
    }
}
