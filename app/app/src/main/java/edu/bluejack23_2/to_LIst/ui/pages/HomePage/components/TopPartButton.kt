package edu.bluejack23_2.to_LIst.ui.pages.HomePage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import edu.bluejack23_2.to_LIst.ui.theme.UISettings


//@Preview
@Composable
fun TopPartBox(onButtonClick: () -> Unit, color: Color = UISettings.homeButtonColor1, icon:ImageVector = Icons.Outlined.CalendarToday, text: String){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val boxWidth = screenWidth / 2 - 30.dp
    Box(
        modifier = Modifier
            .height(80.dp)
            .width(boxWidth)
            .clickable { onButtonClick() }
            .clip(RoundedCornerShape(12.dp))
            .background(Color.LightGray),
    ){
        Column(modifier = Modifier
            .matchParentSize()
            .padding(10.dp)) {
            Row(modifier = Modifier.fillMaxSize()){
                CircleWithIcon(icon = icon, circleColor = color)

                Spacer(modifier = Modifier.width(15.dp))

                var finalFontSize = UISettings.homePageButtonTextSizeSecondary

                if(text =="Today" || text =="All"){
                    finalFontSize = UISettings.homePageButtonTextSizePrimary
                }

                Column(modifier = Modifier.padding(top = 7.dp)){

                    Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.End){
                        Text(text = text, fontWeight = FontWeight.Bold, fontSize =  finalFontSize)
                    }
                }
            }
        }
    }
}


@Composable
fun CircleWithIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconColor: Color = Color.White,
    circleColor: Color = Color.Blue,
    circleSize: Dp = 56.dp,
    iconSize: Dp = 30.dp,
    contentDescription: String? = null
) {
    Box(
        modifier = Modifier
            .size(circleSize)
            .background(circleColor, CircleShape)
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = iconColor,
            modifier = Modifier.size(iconSize)
        )
    }
}