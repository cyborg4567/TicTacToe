package com.example.tictactoe.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.ui.theme.*

@Composable
fun GettingStarted(NavigateToPlayerSelection:() -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.verticalGradient(listOf(gradient1, gradient2)))
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {
        Image(painter = painterResource(id = R.drawable.mainappscreen),
            contentDescription = "",
            modifier = Modifier.size(325.dp))

        Button(onClick = {
            NavigateToPlayerSelection()
        },
            colors = ButtonColors(containerColor = gradient2, contentColor = Color.White, disabledContentColor = Color.Transparent, disabledContainerColor = Color.Transparent)
        ) {
            Row(modifier = Modifier.padding(8.dp).fillMaxWidth(0.4f),
                horizontalArrangement = Arrangement.SpaceBetween) {
            Icon(imageVector = ImageVector.vectorResource(id = R.drawable.icon_play),
                contentDescription ="",
                modifier = Modifier.size(28.dp))
            Text(text = "PLAY",
                fontSize = 25.sp)

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GettingStartedPreview() {
//    GettingStarted()
}