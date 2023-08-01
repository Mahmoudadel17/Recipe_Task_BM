package com.example.compose_application.sessionTask

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_application.R
import com.example.compose_application.todo.Task
import com.example.compose_application.todo.defaultTasks


@Composable
fun TaskCard2(task: Task = defaultTasks[0], onDeleteClick:(Task) -> Unit = {}) {
    var isCompleteClickedState by rememberSaveable { mutableStateOf(task.isFavorite) }
    val completeColor = if (isCompleteClickedState) Color.Green else Color.DarkGray


    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),

        ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon for complete with click listener
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "${task.title} ${task.id} ", fontSize = 30.sp, color = Color.Black)
                Text(text = task.description)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector =  Icons.Default.CheckCircle,
                contentDescription = "complete Task",
                tint = completeColor,
                modifier = Modifier.clickable {
                    isCompleteClickedState = !isCompleteClickedState
                } // Add click listener
            )
            Spacer(modifier = Modifier.width(2.dp))
            Icon(
                imageVector =  Icons.Default.Clear,
                contentDescription = "clear Task",
                modifier = Modifier.clickable {
                    onDeleteClick(task)
                } // Add click listener
            )
        }
    }


}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ListOfTasks() {
    var tasks = rememberSaveable { mutableStateOf(defaultTasks) }
    LazyColumn {
        items(tasks.value.size) {iter ->
            TaskCard2(tasks.value[iter]){backTask ->
                defaultTasks = defaultTasks.filter { it.id != backTask.id }
                tasks.value = defaultTasks
            }
        }
    }

}

















@Composable
fun CardPerson() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(12.dp)

        ){
        Column(
            verticalArrangement = Arrangement.Center,
           ) {
            Image(painter = painterResource(
                id = R.drawable.mobile_login ),
                contentDescription = "Login Image" ,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(text = " John Doe", style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = " Software Engineer")
        }
    }
}