package com.example.setgame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.setgame.CardInterface.*
import com.example.setgame.CardInterface.ColorValue.*
import com.example.setgame.CardInterface.NumberValue.*
import com.example.setgame.CardInterface.ShadingValue.*
import com.example.setgame.CardInterface.ShapeValue.*

@Composable
fun CardDisplay(color: ColorValue, shading: ShadingValue, shape: ShapeValue,
                number: NumberValue, isSelected: Boolean, i: Int, onClick: (input: Int) -> Unit) {
    val cardColor: ColorFilter
    var cardPic = painterResource(id = R.drawable.ic_open_diamond)
    val cardNumber: Int
    var borderColor = Color.DarkGray
    when (color) {
        RED -> {
            cardColor = ColorFilter.tint(Color.Red)
        }
        GREEN -> {
            cardColor = ColorFilter.tint(Color.Green)
        }
        PURPLE -> {
            cardColor = ColorFilter.tint(colorResource(id = R.color.purple_500))
        }
    }
    when {
        shading == OPEN && shape == DIAMOND -> {
            cardPic = painterResource(id = R.drawable.ic_open_diamond)
        }
        shading == OPEN && shape == OVAL -> {
            cardPic = painterResource(id = R.drawable.ic_open_oval)
        }
        shading == OPEN && shape == SQUIGGLE -> {
            cardPic = painterResource(id = R.drawable.ic_open_squiggle)
        }
        shading == STRIPED && shape == DIAMOND -> {
            cardPic = painterResource(id = R.drawable.ic_striped_diamond)
        }
        shading == STRIPED && shape == OVAL -> {
            cardPic = painterResource(id = R.drawable.ic_striped_oval)
        }
        shading == STRIPED && shape == SQUIGGLE -> {
            cardPic = painterResource(id = R.drawable.ic_striped_squiggle)
        }
        shading == SOLID && shape == DIAMOND -> {
            cardPic = painterResource(id = R.drawable.ic_solid_diamond)
        }
        shading == SOLID && shape == OVAL -> {
            cardPic = painterResource(id = R.drawable.ic_solid_oval)
        }
        shading == SOLID && shape == SQUIGGLE -> {
            cardPic = painterResource(id = R.drawable.ic_solid_squiggle)
        }
    }
    when (number) {
        ONE -> {
            cardNumber = 1
        }
        TWO -> {
            cardNumber = 2
        }
        THREE -> {
            cardNumber = 3
        }
    }
    if (isSelected) {
        borderColor = Color.Red
    }

    Card(
        border = BorderStroke(1.dp, borderColor),
        elevation = 0.dp,
        modifier = Modifier
            .width(100.dp)
            .height(60.dp)
            .padding(8.dp)
            .clickable(onClick = { onClick(i) })
    ) {
        Row(modifier = Modifier.wrapContentSize()) {
            Image(
                painter = cardPic,
                colorFilter = cardColor,
                contentDescription = null,
                modifier = Modifier
                    .width(28.dp)
                    .height(50.dp)
            )
            if (cardNumber >= 2) {
                Image(
                    painter = cardPic,
                    colorFilter = cardColor,
                    contentDescription = null,
                    modifier = Modifier
                        .width(28.dp)
                        .height(50.dp)
                )
            }
            if (cardNumber == 3) {
                Image(
                    painter = cardPic,
                    colorFilter = cardColor,
                    contentDescription = null,
                    modifier = Modifier
                        .width(28.dp)
                        .height(50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardDisplayPreview() {
    CardDisplay(
        PURPLE, STRIPED,
            SQUIGGLE, number = TWO, isSelected = true,
            i = 1, onClick = ::click)
}

fun click(i: Int) {

}