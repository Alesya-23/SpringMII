package com.lab.mii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.lab.mii.databinding.ActivityLabOneBinding

class LabOneActivity : AppCompatActivity() {
    private lateinit var chart: LineChart
    private lateinit var labOneActivityBinding: ActivityLabOneBinding
    private var labOne: LabOne = LabOne()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        labOneActivityBinding = ActivityLabOneBinding.inflate(layoutInflater)
        val view = labOneActivityBinding.root
        setContentView(view)
        chart = labOneActivityBinding.chart
        actionButtonAdd()
    }

    private fun actionButtonAdd() {
        labOneActivityBinding.buttonAdd.setOnClickListener {
            if (addParameters()) {
                labOne.addPoint(chart, labOneActivityBinding)
            } else Toast.makeText(
                this,
                getString(R.string.you_dont_in_params_toast),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun addParameters(): Boolean {
        with(labOneActivityBinding) {
            val aParam = aParameterInput.text.toString()
            val bParam = bParameterInput.text.toString()
            val cParam = cParameterInput.text.toString()
            val dParam = dParameterInput.text.toString()
            if (aParam.isNotEmpty() && bParam.isNotEmpty() && cParam.isNotEmpty() && dParam.isNotEmpty()) {
                labOne.aParam = aParam.toInt()
                labOne.bParam = bParam.toInt()
                labOne.cParam = cParam.toInt()
                labOne.dParam = dParam.toInt()
                return true
            }
            return false
        }
    }
}