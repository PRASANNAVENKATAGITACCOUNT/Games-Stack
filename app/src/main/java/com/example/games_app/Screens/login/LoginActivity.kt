package com.example.games_app.Screens.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.example.games_app.Screens.dashboard.DashboardActivity
import com.example.games_app.ui.theme.GameAppTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GameAppTheme {
                Surface {
                    LoginScreen(){email,password->
                        val intent = Intent(this,DashboardActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}