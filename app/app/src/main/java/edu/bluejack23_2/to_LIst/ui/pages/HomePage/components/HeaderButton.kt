package edu.bluejack23_2.to_LIst.ui.pages.HomePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val gap = 20.dp
val boxWidth = 0.5f

@Preview(showBackground = true)
@Composable
fun HeaderButton() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Something",
                    modifier = Modifier.padding(20.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(gap))

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Something",
                    modifier = Modifier.padding(20.dp),
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(gap))

        Row() {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Something",
                    modifier = Modifier.padding(20.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(gap))

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = "Something",
                    modifier = Modifier.padding(20.dp),
                    color = Color.White
                )
            }
        }
    }
}