package com.example.firebasefroga

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firebasefroga.pantailak.OngiEtorri
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContent {
            FireBaseFrogaTheme {
                LoginScreen(auth)
            }
        }
    }
}

@Composable
fun LoginScreen(auth: FirebaseAuth){

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var pass by remember {mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            label = {Text("Email")}

        )
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it},
            label = {Text("Password")}

        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly

        ) {
            Button(
                onClick = {
                    Acceder(email,pass,auth, context)

                }
            ) {
                Text("Acceder")
            }
            Button(
                onClick = {
                    RegistroUsuario(email,pass,auth, context)
                }
            ) {
                Text("Registrar")
            }
        }

    }
}

fun RegistroUsuario(email:String,pass:String,auth: FirebaseAuth,context: Context){
    auth.createUserWithEmailAndPassword(email, pass)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(context, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al registrarse", Toast.LENGTH_SHORT).show()
            }
        }
}
fun Acceder(email:String,pass: String,auth: FirebaseAuth, context: Context){
    auth.signInWithEmailAndPassword(email, pass)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(context, "¡Bienvenido de vuelta!", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, OngiEtorri::class.java)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Error al acceder: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
}