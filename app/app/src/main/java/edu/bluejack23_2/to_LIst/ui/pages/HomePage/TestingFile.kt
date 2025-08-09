package edu.bluejack23_2.to_LIst.ui.pages.HomePage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class TestingFile {
    
    @Composable
    fun TestingPreview(){
        Column {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Anjay Mabar")
            }
        }
    }

    @Preview(showBackground = true, showSystemUi = false)
    @Composable
    fun PreviewNumber2(){
        TestingPreview()
    }
}