package edu.pe.cibertec.ventaentradasapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.Coil
import coil.ImageLoader
import edu.pe.cibertec.ventaentradasapp.navigation.Navigation
import edu.pe.cibertec.ventaentradasapp.ui.theme.VentaEntradasAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Coil.setImageLoader(
            ImageLoader.Builder(applicationContext)
                .crossfade(true)
                .build()
        )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    Scaffold (modifier = Modifier.fillMaxSize()) {
                            innerpadding -> Navigation(Modifier.padding(innerpadding), navController)
                    }
                }
            }
        }
    }
}

