package com.example.tpintentsremote

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    companion object{
        val EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY"
    }
    lateinit var textMessage : TextView
    lateinit var reply : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        textMessage = findViewById(R.id.text_message)
        reply = findViewById(R.id.editText_second);
        textMessage.text = message

    }

    fun returnReply(view : View){
        val intent = intent
        intent.putExtra(EXTRA_REPLY, reply.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}