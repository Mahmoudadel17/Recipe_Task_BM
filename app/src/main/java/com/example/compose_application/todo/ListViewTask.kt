package com.example.compose_application.todo

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun TaskList() {

    val ongoingTasks = rememberSaveable { mutableStateOf(defaultTasks.filter { !it.isComplete }) }
    val completedTasks = rememberSaveable { mutableStateOf(defaultTasks.filter { it.isComplete }) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    LazyColumn(state = listState) {
        item {
            if (ongoingTasks.value.isNotEmpty()) {
                Text(text = "Ongoing Tasks", fontSize = 40.sp)
                for (task in ongoingTasks.value) {
                    TaskCard(task = task) {
                        ongoingTasks.value = defaultTasks.filter { !it.isComplete }
                        completedTasks.value = defaultTasks.filter { it.isComplete }
                        if (!listState.isScrollInProgress) {
                            scope.launch {
                                listState.animateScrollToItem(defaultTasks.size)
                            }
                        }
                    }
                }
            }

        }
        item {
            if (completedTasks.value.isNotEmpty()){
                Text(text = "Completed Tasks",  fontSize =40.sp)
                for (task in completedTasks.value){
                    TaskCard(task = task){
                        ongoingTasks.value =  defaultTasks.filter { !it.isComplete}
                        completedTasks.value = defaultTasks.filter { it.isComplete}

                    }
                }
            }
        }

    }


}