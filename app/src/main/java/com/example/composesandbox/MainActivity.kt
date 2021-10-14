package com.example.composesandbox

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesandbox.ui.theme.ComposeSandboxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSandboxTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyScreen()
                }
            }
        }
    }
}

object PolarisColor {
    val Border = Color(0xFFDCDEE0)
    val Surface = Color(0xFFF1F2F3)
    val Primary = Color(0xFF008060)
}

object Dp {
    val Zero = 0.dp
    val One = 1.dp
    val Two = 2.dp
    val Four = 4.dp
    val Eight = 8.dp
    val Twelve = 12.dp
    val Sixteen = 16.dp
    val Twenty = 20.dp
    val TwentyFour = 24.dp
    val ThirtyTwo = 32.dp
}

object Sp {
    val Sixteen = 16.sp
    val Twenty = 20.sp
    val TwentyFour = 24.sp
    val ThirtyTwo = 32.sp
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyScreen()
}

@Composable
fun MyScreen() {
    val context = LocalContext.current

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = { }) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    }
                )

            },
            content = {
                Box(modifier = Modifier.padding(Dp.Sixteen)) {
                    Column {
                        ContentTitle("The title")
                        Spacer(Modifier.size(Dp.Sixteen))
                        VerticalIconWithTextCell(
                            listOf(
                                Cell(icon = Icons.Default.Home, text = "Home"),
                                Cell(icon = Icons.Default.Settings, text = "Settings"),
                                Cell(icon = Icons.Default.ShoppingCart, text = "Store")
                            )
                        ) { Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show() }
                        Spacer(Modifier.size(Dp.ThirtyTwo))
                        HeaderTitleWithPillAction(title = "Add content in sections", action = "Action")
                        Spacer(Modifier.size(Dp.Eight))
                        CardWithComponents()
                    }
                }
            }
        )
    }
}

@Composable
fun ContentTitle(title: String) {
    Text(
        text = title,
        fontSize = Sp.ThirtyTwo
    )
}

@Composable
fun ContentCard(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(Dp.Sixteen),
        border = BorderStroke(Dp.One, PolarisColor.Border),
        elevation = Dp.One
    ) {
        content()
    }
}

@Composable
fun PrimaryButton(text: String, modifier: Modifier = Modifier, clickHandler: () -> Unit = {}) {
    Box(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = { clickHandler() },
            shape = RoundedCornerShape(Dp.Twelve),
            elevation = ButtonDefaults.elevation(defaultElevation = Dp.Zero),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PolarisColor.Primary,
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = text, modifier = Modifier.padding(Dp.Eight)) }
    }
}

@Composable
fun HeaderTitleWithPillAction(title: String, action: String, clickHandler: () -> Unit = {}) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = Sp.Twenty,
            modifier = Modifier
                .align(alignment = CenterVertically)
                .weight(1f)
        )
        Button(
            onClick = { },
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.elevation(defaultElevation = Dp.Zero),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PolarisColor.Surface,
                contentColor = Color.Black
            )
        ) { Text(text = action) }
    }
}

@Composable
fun CardWithComponents() {
    ContentCard {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dp.Sixteen)
        ) {
            Text(text = "This is a card view")
            Spacer(modifier = Modifier.size(Dp.Sixteen))
            PrimaryButton(text = "Primary")
        }
    }

}

data class Cell(val icon: ImageVector, val text: String)

@Composable
fun VerticalIconWithTextCell(cells: List<Cell>, clickHandler: (Cell) -> Unit) {
    ContentCard {
        Column(modifier = Modifier.fillMaxWidth()) {
            cells.forEachIndexed { index, cell ->
                OutlinedButton(
                    onClick = { clickHandler(cell) },
                    contentPadding = PaddingValues(Dp.Sixteen),
                    border = null
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = cell.icon,
                            contentDescription = cell.text,
                            modifier = Modifier
                                .size(Dp.Sixteen)
                                .align(CenterVertically)
                        )
                        Spacer(modifier = Modifier.size(Dp.Twelve))
                        Text(
                            text = cell.text,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .align(CenterVertically)
                        )
                    }

                }
                if (index < cells.size - 1) {
                    Divider(color = PolarisColor.Border, thickness = Dp.One)
                }
            }
        }
    }
}
