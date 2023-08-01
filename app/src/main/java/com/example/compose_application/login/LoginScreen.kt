package com.example.compose_application.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
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
    var emailErrorMessage by remember { mutableStateOf("") }
    var passwordErrorMessage by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var checkBox by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,){

        Image(painter = painterResource(id =R.drawable.mobile_login ),
            contentDescription = "Login Image" ,
            modifier = Modifier.size(200.dp))
//        LoaderAnimation()

        OutlinedTextField(
            label = { Text("Email") },
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                email = it
                emailErrorMessage=""
                            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            trailingIcon = {
                Icon(imageVector = Icons.Filled.Email , contentDescription ="" )
            }
        )
        Text(emailErrorMessage, style = typography.bodyMedium, modifier = Modifier
            .padding(top = 3.dp), color = Color.Red
        )
        OutlinedTextField(
            label = { Text("Password") },
            value = password,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = {
                password = it
                passwordErrorMessage=""
                            },
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            trailingIcon = {
                IconButton(onClick = {
                    showPassword = showPassword.not()
                }) {
                    Icon(imageVector = Icons.Filled.Lock , contentDescription ="" )
                }
            }
        )
        Text(passwordErrorMessage, style = typography.bodyMedium, modifier = Modifier
            .padding(top = 3.dp), color = Color.Red
        )
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = checkBox, onCheckedChange = {
                checkBox = !checkBox
            })
            Spacer(modifier = Modifier.width(2.dp))
            Text("Remember",
                color = Color.DarkGray,
            )
        }
        ButtonClickOn("Login"){
            val (check,emailMessage,passwordMessage) = validationLogin(email,password)

            if (!check) {
                emailErrorMessage = emailMessage
                passwordErrorMessage = passwordMessage
            } else {
                emailErrorMessage = emailMessage
                passwordErrorMessage = passwordMessage
                if (checkBox){
                    Toast.makeText(context, "Login successful! Remember", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
                }

            }
        }
        Row (){
            Text("New to the app?",
                modifier = Modifier
                    .padding(top = 3.dp),
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text("Register",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .clickable {

                    },
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text("Forgot Password?",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier

                    .padding(top = 3.dp)
                    .clickable {

                    },
                color = Color.DarkGray
            )
        }

    }
}


