package com.example.games_app.Screens.dashboard

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.games_app.ui.theme.GameAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class DashboardActivity : ComponentActivity() {
    val activityRef:Activity = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameAppTheme {
                Surface {
                    DashboardUI(activityRef)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardPreview() {
    GameAppTheme {
        //DashboardUI()
    }
}