package com.example.firebasefroga.pantailak

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.firebasefroga.MainActivity
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme

class OngiEtorri: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FireBaseFrogaTheme{
                OngiEtorriScreen()
            }
        }
    }
}

@Composable
fun OngiEtorriScreen (){
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Registro",
            fontSize = 30.sp
        )

        Button(
            onClick = {
                val intent = Intent(context, IkasleActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("Siguiente")
        }
    }
}
