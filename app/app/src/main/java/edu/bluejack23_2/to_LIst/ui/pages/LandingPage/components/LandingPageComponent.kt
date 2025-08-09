package edu.bluejack23_2.to_LIst.ui.pages.LandingPage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.R


@Preview(showBackground = true)
@Composable
fun AppLogo(){
    Image(
        painter = painterResource(id = R.drawable.tolist_logo),
        contentDescription = "toLIst Logo",
        modifier = Modifier.size(220.dp)
    )
}
