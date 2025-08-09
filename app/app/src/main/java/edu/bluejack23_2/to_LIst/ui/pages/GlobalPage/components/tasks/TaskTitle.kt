package edu.bluejack23_2.to_LIst.ui.pages.GlobalPage.components.tasks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun TaskTitle(text : String){
    Text(text = text, fontSize = 16.sp)
}
@Composable
fun TaskDescription(text : String){
    Text(text = text, fontSize = 10.sp, color = Color.Gray)
}