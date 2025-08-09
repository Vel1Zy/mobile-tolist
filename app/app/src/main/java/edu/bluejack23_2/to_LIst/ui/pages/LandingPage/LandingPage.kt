package edu.bluejack23_2.to_LIst.ui.pages.LandingPage


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.bluejack23_2.to_LIst.R
import edu.bluejack23_2.to_LIst.ui.pages.LandingPage.components.AppLogo
import edu.bluejack23_2.to_LIst.viewmodel.GlobalViewModel.GlobalViewModel
import kotlinx.coroutines.launch


class LandingPage {
    @Composable
    fun LandingComposable(navController: NavController) {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        if (GlobalViewModel.getUserInformation() != null) coroutineScope.launch {
            navController.navigate("home")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                AppLogo()
            }

            Column(
                modifier = Modifier.padding(start = 50.dp, end = 50.dp, bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Daily Go to To-Do List",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Organize your tasks, boost productivity, and stay on top of your goals.",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))


                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5F33E1)
                    ),
                    modifier = Modifier
                        .width(320.dp)
                        .height(50.dp)
                ) {
                    Text(text = "Get Started!")
                }
            }
        }
    }
}
