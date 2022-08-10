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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardDisplay(color: CardInterface.ColorValue, shading: CardInterface.ShadingValue, shape: CardInterface.ShapeValue,
                number: CardInterface.NumberValue, isSelected: Boolean, i: Int, onClick: (input: Int) -> Unit) {
    var cardColor: ColorFilter
    var cardPic: Painter
    var cardNumber: Int
    var borderColor = Color.DarkGray
    when (color) {
        CardInterface.ColorValue.RED -> {
            cardColor = ColorFilter.tint(Color.Red)
        }
        CardInterface.ColorValue.GREEN -> {
            cardColor = ColorFilter.tint(Color.Green)
        }
        CardInterface.ColorValue.PURPLE -> {
            cardColor = ColorFilter.tint(colorResource(id = R.color.purple_500))
        }
    }
    if (shading == CardInterface.ShadingValue.OPEN && shape == CardInterface.ShapeValue.DIAMOND) {
        cardPic = painterResource(id = R.drawable.ic_open_diamond)
    }
    else if (shading == CardInterface.ShadingValue.OPEN && shape == CardInterface.ShapeValue.OVAL) {
        cardPic = painterResource(id = R.drawable.ic_open_oval)
    }
    else if (shading == CardInterface.ShadingValue.OPEN && shape == CardInterface.ShapeValue.SQUIGGLE) {
        cardPic = painterResource(id = R.drawable.ic_open_squiggle)
    }
    else if (shading == CardInterface.ShadingValue.STRIPED && shape == CardInterface.ShapeValue.DIAMOND) {
        cardPic = painterResource(id = R.drawable.ic_striped_diamond)
    }
    else if (shading == CardInterface.ShadingValue.STRIPED && shape == CardInterface.ShapeValue.OVAL) {
        cardPic = painterResource(id = R.drawable.ic_striped_oval)
    }
    else if (shading == CardInterface.ShadingValue.STRIPED && shape == CardInterface.ShapeValue.SQUIGGLE) {
        cardPic = painterResource(id = R.drawable.ic_striped_squiggle)
    }
    else if (shading == CardInterface.ShadingValue.SOLID && shape == CardInterface.ShapeValue.DIAMOND) {
        cardPic = painterResource(id = R.drawable.ic_solid_diamond)
    }
    else if (shading == CardInterface.ShadingValue.SOLID && shape == CardInterface.ShapeValue.OVAL) {
        cardPic = painterResource(id = R.drawable.ic_solid_oval)
    }
    else {
        cardPic = painterResource(id = R.drawable.ic_solid_squiggle)
    }
    when (number) {
        CardInterface.NumberValue.ONE -> {
            cardNumber = 1
        }
        CardInterface.NumberValue.TWO -> {
            cardNumber = 2
        }
        CardInterface.NumberValue.THREE -> {
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
    CardDisplay(CardInterface.ColorValue.PURPLE, CardInterface.ShadingValue.STRIPED,
            CardInterface.ShapeValue.SQUIGGLE, number = CardInterface.NumberValue.TWO, isSelected = true,
            i = 1, onClick = ::click)
}

fun click(i: Int) {

}