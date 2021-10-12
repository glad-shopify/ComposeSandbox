package com.example.composesandbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composesandbox.ui.theme.ComposeSandboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSandboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Toolbar("Android")
                }
            }
        }
    }
}

@Composable
fun Toolbar(title: String) {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button, text) = createRefs()
        val titleRef = createRefFor("title")


        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Text", Modifier.constrainAs(text) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            end.linkTo(button.start)
            bottom.linkTo(parent.bottom)
        })

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                start.linkTo(text.end)
                top.linkTo(text.top)
                end.linkTo(parent.end)
                bottom.linkTo(text.bottom)
            }
        ) {
            Text(title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeSandboxTheme {
        Toolbar("Test")
    }
}
