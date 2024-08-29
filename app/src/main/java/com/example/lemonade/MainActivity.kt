package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}


@Composable
fun LemonadeApp() {
    ButtonAndText()
}

@Composable
fun ButtonAndText(modifier: Modifier = Modifier) {

    var currentFrame by remember {
        mutableIntStateOf(1)
    }

    var tapsToSqueezeCount by remember {
        mutableIntStateOf(0)
    }

    var tapsDone = 0

    val imageRes = when(currentFrame) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imageDesc = when(currentFrame) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    val actionText = when(currentFrame) {
        1 -> R.string.tap_the_lemon_tree
        2 -> R.string.keep_tapping
        3 -> R.string.tap_to_drink
        else -> R.string.tap_to_start_again
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Button(
            shape = RoundedCornerShape(10),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFc3ecd2)
            ),
            onClick = {
                when(currentFrame) {
                    1 -> {
                        currentFrame++
                    }
                    2 -> {
                        if (tapsToSqueezeCount == 0)
                            tapsToSqueezeCount = (2..4).random()
                        else if (tapsToSqueezeCount == tapsDone) {
                            currentFrame++
                            tapsToSqueezeCount = 0
                            tapsDone = 0
                        } else {
                            tapsDone++
                        }

                    }
                    3 -> {
                        currentFrame++
                    }
                    else -> {
                        currentFrame = 1
                    }
                }
            }
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = stringResource(imageDesc)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(actionText),
            fontSize = 18.sp
        )
    }
}