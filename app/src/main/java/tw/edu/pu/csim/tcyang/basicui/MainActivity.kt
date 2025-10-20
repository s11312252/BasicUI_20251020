package tw.edu.pu.csim.tcyang.basicui


import android.app.Activity
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
import android.widget.Toast // 引入 Toast 類別
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.ButtonDefaults

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
    // 取得 Context 和 Activity
    val context = LocalContext.current
    // 這裡我們不再在頂部宣告 activity 變數，而是直接在按鈕的 onClick 內部安全地轉換。

    // 固定的作者文字
    val authorText = stringResource(R.string.app_author)

    // 狀態變數：用於切換新文字 (abc / edf)
    val textA = "abc"
    val textB = "edf"
    var currentToggleText by remember {
        mutableStateOf(textA)
    }


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

        // 顯示作者文字
        Text(
            text = authorText,
            fontSize = 20.sp,
            color = Color(0xFF654321)
        )
        Spacer(modifier = Modifier.size(10.dp))

        // 上方的三個圖示 (保持不變)
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

        // LazyRow 滾動動物列表 (保持不變)
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

        // 按鈕和切換文字的整體容器
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            // **第一排：測試按鈕 (單獨一排)**
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(bottom = 5.dp)) {
                // 1. 測試按鈕 (切換 abc/edf)
                Button(onClick = {
                    currentToggleText = if (currentToggleText == textA) textB else textA
                }) {
                    Text(text = "測試按鈕")
                }
            }

            // **中間：顯示可切換的文字 (abc / edf)**
            Text(
                text = currentToggleText,
                fontSize = 24.sp,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 10.dp)
            )


            // **第三排：歡迎修課、展翅飛翔、結束App (三個按鈕一排)**
            Row(horizontalArrangement = Arrangement.Center) {
                // 2. 歡迎修課按鈕 (暫無作用)
                Button(onClick =
                    {
                }) {
                    Text(text = "歡迎修課")
                }

                Spacer(modifier = Modifier.size(10.dp))

                // 3. 展翅飛翔按鈕 (彈出 Toast)
                Button(onClick = {
                    Toast.makeText(context, "展翅飛翔，實現夢想！", Toast.LENGTH_LONG).show()
                }) {
                    Text(text = "展翅飛翔")
                }

                Spacer(modifier = Modifier.size(10.dp))

                // 4. 結束 App 按鈕 (帶有自訂樣式)
                Button(
                    onClick = {
                        // 將 Context 安全地轉換(as?)為 Activity，並呼叫 finish()
                        (context as? Activity)?.finish()
                    },
                    // 設定按鈕顏色為亮藍色
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00BFFF)),
                    // 形狀：將元素的每個角落「切掉」一個直角
                    shape = CutCornerShape(10.dp),
                    // 藍色框線
                    border = BorderStroke(1.dp, Color.Blue),
                    // 陰影
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
                ) {
                    Text(text = "結束App")
                }
            }
        }
    }
}