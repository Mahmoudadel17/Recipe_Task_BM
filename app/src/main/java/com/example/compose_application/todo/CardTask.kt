package com.example.compose_application.todo

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TaskCard(task: Task = defaultTasks[0],onCompleteClick:() -> Unit = {}) {
    var onIconCompletedClick by remember { mutableStateOf(false) }

    var isStarClickedState by remember { mutableStateOf(task.isFavorite) }
    val starColor = if (isStarClickedState) Color(0xFFDAA520) else Color.DarkGray
    val completeColor = if (task.isComplete) Color.Green else Color.DarkGray


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(15.dp),

    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon for complete with click listener
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Task Status",
                tint = completeColor,
                modifier = Modifier.clickable {
                    task.isComplete = !task.isComplete
                    onIconCompletedClick = true
                    // Log.d("test","i am in onClick")
                }

            )


            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(text = task.title, fontSize = 30.sp, color = Color.Black)
                Text(text = task.description)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Icon for favorite with click listener
            Icon(
                imageVector =  Icons.Default.Star,
                contentDescription = "Favorite Task",
                tint = starColor,
                modifier = Modifier.clickable {
                    task.isFavorite = !task.isFavorite
                    isStarClickedState = !isStarClickedState
                } // Add click listener
            )
        }
    }
    LaunchedEffect(onIconCompletedClick) {
        if (onIconCompletedClick) {
            delay(500L)
            // Log.d("test","i am in LaunchedEffect")
            onIconCompletedClick = false
            onCompleteClick()
        }
    }

}

