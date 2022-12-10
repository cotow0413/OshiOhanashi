package com.example.oshiohanashi

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Viewを取得
        var tv: TextView = findViewById(R.id.textView)
        var changewadai: Button = findViewById(R.id.changewadai)

        //クリック処理
        changewadai.setOnClickListener {
            //配列で、文言を用意
            var wadai = arrayOf(
                "最近あった楽しかったこと!",
                "あなたの趣味とその理由!",
                "あなたはたけのこ派、キノコ派？",
                "一番おいしいと思ったコンビニ飯！",
                "生きてて一番残酷だったこと!",
                "生きてて良かったなと感じたこと!",
                "最近の恋模様を教えてください！",
                "今一番したいことは？？",
                "あなたは犬派？ねこ派？"
            )


            //乱数を作ってみる
            var num = java.util.Random().nextInt(wadai.count())

            //表示
            tv.text = wadai.get(num)
        }

        //推し正面写真選択
        var changeoshi: Button = findViewById(R.id.changeoshi)

        //ボタンが押されたらギャラリーを開く
        changeoshi.setOnClickListener()
        {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            startActivityForResult(intent, READ_REQUEST_CODE)
        }
    }

//READ_REQUEST_CODEの定義
        companion object {
            private const val READ_REQUEST_CODE: Int = 42
        }

        //写真が選択された後の動き
        override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
            super.onActivityResult(requestCode, resultCode, resultData)
            if (resultCode != RESULT_OK) {
                return
            }
            when (requestCode) {
                READ_REQUEST_CODE -> {
                    try {
                        resultData?.data?.also { uri ->
                            val inputStream = contentResolver?.openInputStream(uri)
                            val image = BitmapFactory.decodeStream(inputStream)
                            val imageView = findViewById<ImageView>(R.id.oshi)
                            imageView.setImageBitmap(image)
                        }
                    } catch (e: Exception) {
                        Toast.makeText(this, "エラーが発生しました", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
