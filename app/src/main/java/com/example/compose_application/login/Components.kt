package com.example.compose_application.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_application.ui.theme.PurpleGrey80


fun validationLogin(email:String,password:String): Pair<Boolean, String>{
    if (email.isEmpty() || password.isEmpty()){
        return Pair(false,"Please enter both email and password fields*")
    }else if(!isValidEmail(email)){
        return Pair(false,"Please enter valid email*")
    }else if (password.length<8){
        return Pair(false,"Please enter valid password (>8 char)*")
    }
    return Pair(true,"")

}


fun isValidEmail(email: String): Boolean {
    // Regular expression pattern to validate the email format
    val pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    // Check if the provided email matches the pattern
    return email.matches(Regex(pattern))
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextInput(textLabel:String ) {
    var userText by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        label = { Text(textLabel) },
        value = userText,
        onValueChange = {userText = it},
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
    )

}


@Composable
fun ButtonClickOn(buttonText:String ,onButtonClick:() -> Unit ) {
    Button (colors = ButtonDefaults.buttonColors(containerColor =PurpleGrey80),
        enabled = true,
        onClick = {onButtonClick()},
        modifier = Modifier
            .padding(8.dp, top = 150.dp)
            .fillMaxWidth(1f)


    ){
        Text(text = buttonText, fontSize = 30.sp)
    }
}

