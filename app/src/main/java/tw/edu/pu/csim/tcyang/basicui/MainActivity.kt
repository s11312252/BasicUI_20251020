package tw.edu.pu.csim.tcyang.basicui


import android.app.Activity
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.DisposableEffect

import tw.edu.pu.csim.tcyang.basicui.ui.theme.BasicUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Main(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val authorText = stringResource(R.string.app_author)

    val textA = "abc"
    val textB = "edf"
    var currentToggleText by remember {
        mutableStateOf(textA)
    }
    var mper: MediaPlayer? by remember { mutableStateOf(null) }



    val Animals = listOf(
        R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9
    )

    val AnimalsName = listOf(
        "鴨子", "企鵝",
        "青蛙", "貓頭鷹", "海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞"
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.app_title),
            fontSize = 25.sp,
            color = Color.Blue,
            fontFamily = FontFamily(Font(R.font.kai))
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = authorText,
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )
        Spacer(modifier = Modifier.size(10.dp))

        Row {
            Image(
                painter = painterResource(id = R.drawable.android),
                contentDescription = "Android 圖示",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Yellow),
                alpha = 0.6f,
            )

            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Compose icon",
                modifier = Modifier.size(100.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.firebase),
                contentDescription = "Firebase icon",
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.size(10.dp))

        LazyRow {
            items(51) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(text = "$index:")
                    Text(text = AnimalsName[index % 10])

                    Image(
                        painter = painterResource(id = Animals[index % 10]),
                        contentDescription = "可愛動物",
                        modifier = Modifier
                            .size(60.dp)
                            .padding(start = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(10.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(bottom = 5.dp)) {
                Button(onClick = {
                    currentToggleText = if (currentToggleText == textA) textB else textA
                }) {
                    Text(text = "測試按鈕")
                }
            }

            Text(
                text = currentToggleText,
                fontSize = 24.sp,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 10.dp)
            )



            Row(horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        mper = MediaPlayer.create(context, R.raw.tcyang)
                        mper?.start()
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.33f)
                        .fillMaxHeight(0.8f),
                    colors = buttonColors(Color.Green)

                ) {
                    Text(text = "歡迎修課")
                }

                Spacer(modifier = Modifier.size(10.dp))

                Button(onClick = {

                    mper = MediaPlayer.create(context, R.raw.fly)
                    mper?.start()

                    Toast.makeText(context, "展翅飛翔，實現夢想！", Toast.LENGTH_LONG).show()
                }   , modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.4f),
                    colors = buttonColors(Color.Blue)
                )
                {
                    Text(text = "展翅飛翔")
                }

                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        (context as? Activity)?.finish()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),
                    shape = CutCornerShape(10.dp),
                    border = BorderStroke(1.dp, Color.Blue),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                ) {
                    Text(text = "結束App")
                }
            }
        }
    }
}