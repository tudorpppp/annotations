package com.example.annotationsandroidsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.annotationsandroidsampleapp.ui.main.MainFragment
import com.example.annotationsandroidsampleapp.ui.main.SecondFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    fun navigateToSecond(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SecondFragment.newInstance())
            .commitNow()
    }

}