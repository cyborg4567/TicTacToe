package com.example.tictactoe.Screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.PressGestureScope
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.R
import com.example.tictactoe.Win
import com.example.tictactoe.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(playerOne:String, playerTwo:String) {

    val playerAturn = remember { mutableStateOf(true) }

    val moves = remember {
        mutableStateListOf<Boolean?>(null, null, null, null, null, null, null, null, null)
    }

    val win = remember { mutableStateOf<Win?>(null) }

    val onTap: (Offset) -> Unit = {
        if (playerAturn.value && win.value == null) {
            val x = (it.x / 333).toInt()
            val y = (it.y / 333).toInt()
            val posInMoves = y * 3 + x
            if (moves[posInMoves] == null) {
                moves[posInMoves] = true
                playerAturn.value = false
                win.value = checkEndGame(moves)
            }
        }
    }

    val onTapb: PressGestureScope.(Offset) -> Unit = {
        if (!playerAturn.value && win.value == null) {
            val x = (it.x / 333).toInt()
            val y = (it.y / 333).toInt()
            val i = y * 3 + x
            if (moves[i] == null) {
                moves[i] = false
                playerAturn.value = true
                win.value = checkEndGame(moves)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        gradient1, gradient2
                    )
                )
            )
    ) {
        Header(playerAturn = playerAturn.value, playerOne = playerOne, playerTwo = playerTwo)
        Spacer(modifier = Modifier.height(60.dp))
        Board(p =moves , moves =moves , onTap = onTap , onTapb = onTapb)
    }

    if (win.value != null) {
        var winScreen by remember { mutableStateOf(false) }
        LaunchedEffect(Unit) {
            delay(500)
            winScreen = true
        }
        if (winScreen) {
            when (win.value) {
                Win.PLAYERA -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(lightGrey)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(270.dp)
                                .width(270.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    brush = Brush.radialGradient(
                                        listOf(gradient1, gradient2)
                                    )
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_trophy),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(170.dp)
                                        .padding(bottom = 15.dp)
                                )
                                Text(
                                    text = "$playerOne Won!!",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Button(
                                    onClick = {
                                        playerAturn.value = true
                                        win.value = null
                                        for (i in 0..8) {
                                            moves[i] = null
                                        }
                                    }, colors = ButtonColors(
                                        containerColor = gradient2,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent
                                    )
                                ) {
                                    Text(
                                        text = "REPLAY",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                Win.PLAYERB -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(lightGrey)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(270.dp)
                                .width(270.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    brush = Brush.radialGradient(
                                        listOf(gradient1, gradient2)
                                    )
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_trophy),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(170.dp)
                                        .padding(bottom = 15.dp)
                                )
                                Text(
                                    text = "$playerTwo Won!!",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Button(
                                    onClick = {
                                        playerAturn.value = true
                                        win.value = null
                                        for (i in 0..8) {
                                            moves[i] = null
                                        }
                                    }, colors = ButtonColors(
                                        containerColor = gradient2,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent
                                    )
                                ) {
                                    Text(
                                        text = "REPLAY",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }

                }

                Win.DRAW -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(lightGrey)
                    ) {
                        Box(
                            modifier = Modifier
                                .height(270.dp)
                                .width(270.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(
                                    brush = Brush.radialGradient(
                                        listOf(gradient1, gradient2)
                                    )
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.baseline_cancel_24),
                                    contentDescription = null,
                                    modifier = Modifier.size(150.dp)
                                )
                                Text(
                                    text = "Draw",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Button(
                                    onClick = {
                                        playerAturn.value = true
                                        win.value = null
                                        for (i in 0..8) {
                                            moves[i] = null
                                        }
                                    }, colors = ButtonColors(
                                        containerColor = gradient2,
                                        contentColor = Color.White,
                                        disabledContentColor = Color.Transparent,
                                        disabledContainerColor = Color.Transparent
                                    )
                                ) {
                                    Text(
                                        text = "REPLAY",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                else -> {}
            }
        }

    }
}


fun checkEndGame(m: List<Boolean?>): Win? {
    var win: Win? = null
    if ((m[0] == true && m[1] == true && m[2] == true) ||
        (m[3] == true && m[4] == true && m[5] == true) ||
        (m[6] == true && m[7] == true && m[8] == true) ||
        (m[0] == true && m[3] == true && m[6] == true) ||
        (m[1] == true && m[4] == true && m[7] == true) ||
        (m[2] == true && m[5] == true && m[8] == true) ||
        (m[0] == true && m[4] == true && m[8] == true) ||
        (m[2] == true && m[4] == true && m[6] == true))
            win = Win.PLAYERA
    if ((m[0] == false && m[1] == false && m[2] == false) ||
        (m[3] == false && m[4] == false && m[5] == false) ||
        (m[6] == false && m[7] == false && m[8] == false) ||
        (m[0] == false && m[3] == false && m[6] == false) ||
        (m[1] == false && m[4] == false && m[7] == false) ||
        (m[2] == false && m[5] == false && m[8] == false) ||
        (m[0] == false && m[4] == false && m[8] == false) ||
        (m[2] == false && m[4] == false && m[6] == false))
            win = Win.PLAYERB

    if (win == null) {
        var available = false
        for (i in 0..8) {
            if (m[i] == null) {
                available = true
            }
        }
        if (!available) {
            win = Win.DRAW
        }
    }
    return win
}


@Composable
fun Header(playerAturn: Boolean,playerOne:String, playerTwo:String) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(top = 70.dp)
                .height(180.dp)
                .width(130.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            border = BorderStroke(
                width = 1.dp, color = if (playerAturn) {
                    Color.White
                } else {
                    Color.Black
                }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            listOf(
                                gradient1, gradient2
                            )
                        )
                    )
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.playera),
                    modifier = Modifier.size(60.dp),
                    contentDescription = null
                )
                Text(
                    text = playerOne,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                    )
                Image(
                    painter = painterResource(id = R.drawable.tx),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(30.dp))
        Card(
            modifier = Modifier
                .padding(top = 70.dp)
                .height(180.dp)
                .width(130.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            border = BorderStroke(
                width = 1.dp, color = if (playerAturn) {
                    Color.Black
                } else {
                    Color.White
                }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            listOf(
                                gradient1, gradient2
                            )
                        )
                    )
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.playerb),
                    modifier = Modifier.size(60.dp),
                    contentDescription = null
                )
                Text(
                    text = playerTwo,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Image(painter = painterResource(id = R.drawable.to),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp))
            }
        }
    }
}


@Composable
fun Board(p: List<Boolean?>,
          moves: List<Boolean?>,
          onTap:(Offset)-> Unit,
          onTapb: PressGestureScope.(Offset) -> Unit
) {
    Card(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(32.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = onTap,
                    onPress = onTapb
                )
            },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.radialGradient(listOf(gradient1, gradient2))
            )
        )
        {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {

                }
                Row(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {

                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(1f)
                        .background(Color.LightGray)
                ) {

                }
                Column(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight(1f)
                        .background(Color.LightGray)
                ) {

                }
            }
            Column(
                modifier = Modifier.fillMaxSize(1f)
            )
            {
                for(i in 0..2){
                    Row(
                        modifier = Modifier.weight(1f)
                    ) {
                        for (j in 0..2) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                GetComposableFromMove(move = moves[i*3+j] )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[0] == true && p[1] == true && p[2] == true) ||
                    (p[0] == false && p[1] == false && p[2] == false)
                ) {
                    //insert up line image
                    Image(painter = painterResource(id = R.drawable.upt),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize())
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[3] == true && p[4] == true && p[5] == true) ||
                    (p[3] == false && p[4] == false && p[5] == false)
                ) {
                    Box(modifier = Modifier
                        .height(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                    ) {

                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[6] == true && p[7] == true && p[8] == true) ||
                    (p[6] == false && p[7] == false && p[8] == false)
                ) {
                   Image(
                       painter = painterResource(id = R.drawable.dut),
                       contentDescription = null,
                       modifier = Modifier.fillMaxSize()
                   )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[0] == true && p[3] == true && p[6] == true) ||
                    (p[0] == false && p[3] == false && p[6] == false)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rgt),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[1] == true && p[4] == true && p[7] == true) ||
                    (p[1] == false && p[4] == false && p[7] == false)
                ) {
                    Box(modifier = Modifier
                        .fillMaxHeight()
                        .width(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                    ){

                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[2] == true && p[5] == true && p[8] == true) ||
                    (p[2] == false && p[5] == false && p[8] == false)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lft),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[0] == true && p[4] == true && p[8] == true) ||
                    (p[0] == false && p[4] == false && p[8] == false)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tcr),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize()
            )
            {
                if(
                    (p[2] == true && p[4] == true && p[6] == true) ||
                    (p[2] == false && p[4] == false && p[6] == false)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tcrr),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

        }
    }
}


@Composable
fun GetComposableFromMove(move: Boolean?) {
    when(move) {
        true -> Image(
            painter = painterResource(id = R.drawable.tx) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        )
        false -> Image(
            painter = painterResource(id = R.drawable.to) ,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        )
        null -> Image(
            painter = painterResource(id = R.drawable.nll) ,
            contentDescription = null
        )
    }
}

