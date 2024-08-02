package com.example.tictactoe.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.ui.theme.*

@Composable
fun playerSelection(NavigateToOneNameScreen:() -> Unit,NavigateToTwoNameScreen:() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(gradient1, gradient2))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Choose Player",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)

        Image(painter = painterResource(id = R.drawable.iconexport),
            contentDescription = "",
            modifier = Modifier.size(250.dp))

        Column(modifier = Modifier.padding(10.dp)) {
        Button(
            onClick = {
                NavigateToOneNameScreen()
                      },
            colors = ButtonColors(
                containerColor = gradient2,
                contentColor = Color.White,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = "One Player",
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {
                NavigateToTwoNameScreen()
                      },
            colors = ButtonColors(
                containerColor = gradient2,
                contentColor = Color.White,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = "Two Player",
                fontSize = 20.sp
            )
        }
        }
    }
}

@Preview
@Composable
private fun playerSelectionPreview() {
//    playerSelection()
}