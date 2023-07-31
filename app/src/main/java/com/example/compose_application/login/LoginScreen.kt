package com.example.compose_application.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_application.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,){

        Image(painter = painterResource(id =R.drawable.mobile_login ),
            contentDescription = "Login Image" ,
            modifier = Modifier.size(200.dp))

        OutlinedTextField(
            label = { Text("Email") },
            value = email,
            onValueChange = {email = it},
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
        )
        OutlinedTextField(
            label = { Text("Password") },
            value = password,
            onValueChange = {password = it},
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
        )
        if(errorMessage.isNotEmpty()){
            Text(errorMessage, style = typography.bodyMedium, modifier = Modifier
                .padding(top = 30.dp), color = Color.Red
            )
        }
        ButtonClickOn("Login"){
            val (check,message) = validationLogin(email,password)

            if (!check) {
                errorMessage = message
            } else {
                errorMessage = message
                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


