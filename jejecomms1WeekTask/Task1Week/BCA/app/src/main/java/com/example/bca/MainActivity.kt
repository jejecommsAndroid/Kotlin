package com.example.bca
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bca.di.appModule
import com.example.bca.presentation.navigation.AppNavGraph
import com.example.bca.ui.theme.BCATheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            BCATheme {
                AppNavGraph()
            }
        }
    }
}
