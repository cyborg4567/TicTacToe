package com.example.tictactoe.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun EnterTwoPlayerName(NavigateToPlayerScreen:(String,String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(listOf(gradient1, gradient2))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        var playerOne by remember { mutableStateOf("") }
        var playerTwo by remember { mutableStateOf("") }

        Text(
            text = "Enter Name",
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.iconexport),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "First Player Name:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(value = playerOne,
                onValueChange = { playerOne = it },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    ),
                label = { Text(text = "Enter Player One Name") },

            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(10.dp))

            Text(
                text = "Second Player Name:",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.padding(8.dp)
            )

            OutlinedTextField(value = playerTwo,
                onValueChange = { playerTwo = it },
                modifier = Modifier.padding(8.dp),
                shape = RoundedCornerShape(50),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                ),
                label = { Text(text = "Enter Player Two Name") }
            )

            Spacer(modifier = Modifier.fillMaxWidth().padding(10.dp))

            Button(
                onClick = { NavigateToPlayerScreen(playerOne,playerTwo) },
                modifier = Modifier.padding(8.dp),
                colors = ButtonColors(
                    containerColor = gradient2,
                    contentColor = Color.White,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            ) {
                Text(
                    text = "Submit",
                    fontSize = 20.sp
                )
            }

        }
    }
}

@Preview
@Composable
private fun EnterTwoPlayerNamePreview() {
//    EnterTwoPlayerName()
}