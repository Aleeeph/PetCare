package cl.petcare.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.petcare.app.ui.navigation.AppNavHost
import cl.petcare.app.ui.theme.PetCareTheme
import cl.petcare.app.viewmodel.SessionViewModel
import cl.petcare.app.viewmodel.appViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sessionVm: SessionViewModel = appViewModel { SessionViewModel(it.prefs) }
            val darkPref by sessionVm.darkMode.collectAsStateWithLifecycle()
            PetCareTheme(darkTheme = darkPref ?: isSystemInDarkTheme()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(sessionVm)
                }
            }
        }
    }
}
