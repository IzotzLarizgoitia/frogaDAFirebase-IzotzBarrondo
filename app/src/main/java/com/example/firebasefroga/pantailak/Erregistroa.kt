package com.example.firebasefroga.pantailak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firebasefroga.Acceder
import com.example.firebasefroga.RegistroUsuario
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth
class Erregistroa : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            FireBaseFrogaTheme{
                ErregistroaScreen(auth)
            }
        }
    }
}
@Composable
fun ErregistroaScreen(auth: FirebaseAuth){
    val context = LocalContext.current

    var izena by remember { mutableStateOf("") }
    var abizena by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pass by remember {mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = izena,
            onValueChange = { izena = it},
            label = {Text("Izena")}

        )
        OutlinedTextField(
            value = abizena,
            onValueChange = { abizena = it},
            label = {Text("Abizena")}

        )
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


                }
            ) {
                Text("Aceptar")
            }

        }

    }
}