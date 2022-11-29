package com.example.todoacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todoacademy.TodoAcademy.Companion.preferencias
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), OnFragmentActionsListener{

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!preferencias.getSesion()){
            val intent1: Intent = Intent(applicationContext, Login::class.java).apply {
            }
            startActivity(intent1)
        }

        bottomNavigationView=findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.chat_menu->{
                    loadChatFragment()
                }
                R.id.home_menu->{
                    loadHomeFragment()
                }
                R.id.user_menu->{
                    loadUserFragment()
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun loadChatFragment(){
        title="Chat"
        val chatFragment=Chat()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, chatFragment, "chatFragment")
        fragmentTransaction.commit()
    }

    private fun loadHomeFragment(){
        title="Home"
        val HomeFragment=Home()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, HomeFragment, "HomeFragment")
        fragmentTransaction.commit()
    }

    private fun loadUserFragment(){
        title="User"
        val UserFragment=User()
        val fragmentTransaction=supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, UserFragment, "HomeFragment")
        fragmentTransaction.commit()
    }

    override fun onClickFragmentButton() {
        preferencias.exitSesion()
        startActivity(Intent(this,Login::class.java))
    }

    override fun chat() {
        startActivity(Intent(this,ChatActivity::class.java))
    }




}