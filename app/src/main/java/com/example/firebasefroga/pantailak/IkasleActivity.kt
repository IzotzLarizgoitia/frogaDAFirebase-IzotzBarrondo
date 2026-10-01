package com.example.firebasefroga.pantailak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.firebasefroga.ui.theme.FireBaseFrogaTheme
import com.google.firebase.firestore.FirebaseFirestore


class IkasleActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FireBaseFrogaTheme{
                IkasleActivityScreen()
            }
        }
    }
}
@Composable
fun IkasleActivityScreen(){
    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    var text by remember {mutableStateOf("Oraindik ez dago daturik")}
    var nan by remember { mutableStateOf("") }
    var izena by remember {mutableStateOf("") }
    var abizena by remember {mutableStateOf("") }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nan,
            onValueChange = { nan = it},
            label = {Text("NAN-a")}

        )
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item{
                Text(
                    text = text
                )
                Button(
                    onClick = {

                    }
                ) {
                    Text("Gorde")
                }
                Button(
                    onClick = {

                    }
                ) {
                    Text("Ezabatu")
                }
                Button(
                    onClick = {
                        Erakutzi(db){text= it}

                    }
                ) {
                    Text("Erakutzi")
                }
                Button(
                    onClick = {

                    }
                ) {
                    Text("Itzuli")
                }
            }
        }



    }
}

fun Erakutzi(
    db: FirebaseFirestore,
    text : (String) -> Unit
) {
    db.collection("Ikasleak").get()
        .addOnSuccessListener { listaIkasleak ->
            var datuak = ""
            for (ikaslea in listaIkasleak){
                datuak += "${ikaslea.id} : ${ikaslea.data}\n"
            }
            text(if (datuak.isEmpty()) "Ez dago daturik" else datuak)
    }.addOnFailureListener { e ->
            text("Error: ${e.message}")
    }

    }
