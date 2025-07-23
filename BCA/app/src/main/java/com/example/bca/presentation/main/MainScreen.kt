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
import com.example.bca.presentation.cards.CardsScreen
import com.example.bca.presentation.contact.ContactsScreen
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
            composable(Screen.Contacts.route) { ContactsScreen() }
            composable(Screen.Cards.route) { CardsScreen() }
            composable(Screen.Scan.route) { ScanScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onItemClick: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Screen.Contacts.route,
            onClick = { onItemClick(Screen.Contacts.route) },
            icon = { Icon(Icons.Default.ContactPage, contentDescription = "Contacts") },
            label = { Text("Contacts") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Cards.route,
            onClick = { onItemClick(Screen.Cards.route) },
            icon = { Icon(Icons.Default.CreditCard, contentDescription = "Cards") },
            label = { Text("Cards") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Scan.route,
            onClick = { onItemClick(Screen.Scan.route) },
            icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "Scan") },
            label = { Text("Scan") }
        )
        NavigationBarItem(
            selected = currentRoute == Screen.Profile.route,
            onClick = { onItemClick(Screen.Profile.route) },
            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}
