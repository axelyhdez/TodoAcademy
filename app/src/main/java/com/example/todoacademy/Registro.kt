package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.todoacademy.data.Message
import com.example.todoacademy.utils.Constants
import kotlinx.android.synthetic.main.fragment_chat.*

class Registro : AppCompatActivity() {

    private lateinit var btnSigFinal: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        btnSigFinal=findViewById(R.id.btnSigFinal)

        btnSigFinal.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }

}