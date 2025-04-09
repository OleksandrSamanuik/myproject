package com.example.myapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonFirst = findViewById<Button>(R.id.buttonFirst)
        val buttonSecond = findViewById<Button>(R.id.buttonSecond)

        buttonFirst.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        buttonSecond.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val fromScreen = result.data?.getStringExtra("source")
                Toast.makeText(this, "Повернулись з: $fromScreen", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.buttonFirst).setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            resultLauncher.launch(intent)
        }

        findViewById<Button>(R.id.buttonSecond).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonFirst).setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivityForResult(intent, 1)
        }

        findViewById<Button>(R.id.buttonSecond).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val fromScreen = data?.getStringExtra("source")
            Toast.makeText(this, "Повернулись з: $fromScreen", Toast.LENGTH_SHORT).show()
        }
    }
}
