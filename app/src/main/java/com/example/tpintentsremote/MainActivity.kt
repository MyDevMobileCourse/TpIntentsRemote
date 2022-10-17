package com.example.tpintentsremote

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MESSAGE = "com.example.tpintentsremote.twoactivities.extra.MESSAGE"
        const val TEXT_REQUEST = 1
    }
    private val LOG_TAG = MainActivity::class.java.simpleName
    lateinit var editText: EditText
    lateinit var mReplyHeadTextView: TextView
    lateinit var mReplyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                val reply = data?.getStringExtra(SecondActivity.EXTRA_REPLY)
                mReplyHeadTextView.visibility = View.VISIBLE
                mReplyTextView.text = reply
                mReplyTextView.visibility = View.VISIBLE
            }
        }
    }

    fun launchSecondActivity(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, editText.text.toString())
        resultLauncher.launch(intent)
    }
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val reply = data?.getStringExtra(SecondActivity.EXTRA_REPLY)
            mReplyHeadTextView.visibility = View.VISIBLE
            mReplyTextView.text = reply
            mReplyTextView.visibility = View.VISIBLE
        }
    }
}