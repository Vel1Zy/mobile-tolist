package edu.bluejack23_2.to_LIst.ui.theme

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class UISettings {
    companion object {

        val pagePadding = 20.dp

        val titleFontSize = 50.sp
        val titleFontWeight = FontWeight.Medium

        val titleFontSizeSecondary = 35.sp

        val bottomFontSize = 15.sp
        val topBarFontSize = 30.sp
        val profileTextSizePrimary = 20.sp
        val profileTextSizeSecondary = 16.sp

        val homePageButtonTextSizePrimary = 17.sp
        val homePageButtonTextSizeSecondary = 15.sp

        val fullScreenModifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()



        // MAIN THEME

        val primaryColor = Color(0xff5F33E1)
        val secondaryColor = Color(0xffF4F0FF)
        val profileTextColor = Color(0xff7E7E7E)
        val buttonTextColor = Color.White

        // BUTTONS
//        val updateButtonColor = Color(0xff)

        val homeButtonColor1 = Color(0xff0887FF)
        val homeButtonColor2 = Color(0xffFD453A)
        val homeButtonColor3 = Color(0xff636268)
        val homeButtonColor4 = Color(0xffFF9F0A)

        val homeIconColor = Color.White


        // NAVBAR

        val bottomNavBarColor = Color(0xffE4DAFF)
        val topNavBarColor = Color(0xffE4DAFF)

        // SCHEDULED
        val scheduledIntervalTextSize = 24.sp
        val scheduledTextStyle = TextStyle(
            fontWeight =  FontWeight.SemiBold,
            fontSize = scheduledIntervalTextSize
        )
        val noScheduledTextStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )




    }
}