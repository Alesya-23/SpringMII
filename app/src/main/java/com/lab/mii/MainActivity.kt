package com.lab.mii

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lab.mii.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityBinding.root
        setContentView(view)
        actionButtons()
    }

    private fun actionButtons() {
        with(activityBinding) {
            buttonLabOne.setOnClickListener {
                val intent = Intent(this@MainActivity, LabOneActivity::class.java)
                startActivity(intent)
            }
            buttonLabTwo.setOnClickListener {
                val intent = Intent(this@MainActivity, LabTwoActivity::class.java)
                startActivity(intent)
            }
            buttonLabThree.setOnClickListener {
                val intent = Intent(this@MainActivity, LabThreeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}