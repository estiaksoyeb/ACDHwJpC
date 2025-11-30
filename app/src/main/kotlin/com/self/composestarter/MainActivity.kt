package com.example.composestarter

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the controller to change icon colors
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        setContent {
            // 2. Define the color (White)
            val systemBarColor = Color.White

            // 3. AUTO-DETECT ICON COLOR
            // If the color is bright (luminance > 0.5), we use DARK icons. 
            // If the color is dark, we use LIGHT icons.
            val useDarkIcons = systemBarColor.luminance() > 0.5f

            // 4. APPLY SETTINGS
            // SideEffect ensures this runs every time the screen composes successfully
            SideEffect {
                val colorInt = systemBarColor.toArgb()

                // Set the bar colors
                window.statusBarColor = colorInt
                window.navigationBarColor = colorInt
                
                // Set the underlying window background to match
                window.setBackgroundDrawable(ColorDrawable(colorInt))

                // FIX: Pass 'true' to these if the background is light, so icons become black
                insetsController.isAppearanceLightStatusBars = useDarkIcons
                insetsController.isAppearanceLightNavigationBars = useDarkIcons
            }

            // This is your entry point
            AppEntryPoint()
        }
    }
}

@Composable
fun AppEntryPoint() {
    // A simple container filling the screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello, Jetpack Compose!",
            color = Color.Black,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppEntryPoint()
}