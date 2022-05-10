package com.lab.mii

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lab.mii.databinding.ActivityLabThreeBinding

const val LIST_POINTS = "LIST_POINTS"

class LabThreeActivity : AppCompatActivity() {
    private lateinit var chart: LineChart
    private lateinit var labThreeActivityBinding: ActivityLabThreeBinding
    private var labThreeOneGraph = LabThree()
    private var labThreeTwoGraph = LabThree()
    private var aParam: String? = null
    private var bParam: String? = null
    private var cParam: String? = null
    private var dParam: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_three)
        labThreeActivityBinding = ActivityLabThreeBinding.inflate(layoutInflater)
        val view = labThreeActivityBinding.root
        setContentView(view)
        chart = labThreeActivityBinding.chart
        actionGetDataPointsButton()
        actionOneGraphButtons()
        actionTwoGraphButtons()
    }

    private fun addPointsToChart() {
        val dataSetOne = LineDataSet(labThreeOneGraph.entries, getString(R.string.trapation_1))
        val dataSetTwo = LineDataSet(labThreeTwoGraph.entries, getString(R.string.trapation_2))
        // Линии графиков соберем в один массив
        dataSetOne.color = Color.GREEN
        dataSetTwo.color = Color.RED
        dataSetOne.setDrawFilled(true)
        val dataSets: ArrayList<ILineDataSet> = ArrayList()
        dataSets.add(dataSetOne)
        dataSets.add(dataSetTwo)
        // Создадим переменную  данных для графика
        val data = LineData(dataSets)
        chart.data = data
        // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
        chart.invalidate()
        // Создадим переменную данных для графика
    }

    private fun addParameters(labThree: LabThree): Boolean {
        if (aParam?.isNotEmpty() == true && bParam?.isNotEmpty() == true
            && cParam?.isNotEmpty() == true && dParam?.isNotEmpty() == true
        ) {
            labThree.aParam = aParam?.toInt()
            labThree.bParam = bParam?.toInt()
            labThree.cParam = cParam?.toInt()
            labThree.dParam = dParam?.toInt()
            return true
        }
        return false
    }

    private fun actionOneGraphButtons() {
        labThreeActivityBinding.buttonAddToOne.setOnClickListener {
            aParam = labThreeActivityBinding.aParameterInput.text.toString()
            bParam = labThreeActivityBinding.bParameterInput.text.toString()
            cParam = labThreeActivityBinding.cParameterInput.text.toString()
            dParam = labThreeActivityBinding.dParameterInput.text.toString()
            if (addParameters(labThreeOneGraph)) {
                labThreeOneGraph.addPoint(labThreeActivityBinding.xCoordinateInput.text.toString())
                addPointsToChart()
            } else Toast.makeText(
                this,
                getString(R.string.you_dont_in_params_toast),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun actionTwoGraphButtons() {
        labThreeActivityBinding.buttonAddToTwo.setOnClickListener {
            aParam = labThreeActivityBinding.a2ParameterInput.text.toString()
            bParam = labThreeActivityBinding.b2ParameterInput.text.toString()
            cParam = labThreeActivityBinding.c2ParameterInput.text.toString()
            dParam = labThreeActivityBinding.d2ParameterInput.text.toString()
            if (addParameters(labThreeTwoGraph)) {
                labThreeTwoGraph.addPoint(labThreeActivityBinding.x2CoordinateInput.text.toString())
                addPointsToChart()
            } else Toast.makeText(
                this,
                getString(R.string.you_dont_in_params_toast),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun actionGetDataPointsButton() {
        labThreeActivityBinding.buttonSeeDataPoints.setOnClickListener {
            val intent = Intent(this@LabThreeActivity, DataPointsActivity::class.java)
            intent.putStringArrayListExtra(
                LIST_POINTS,
                (labThreeOneGraph.entries + labThreeTwoGraph.entries) as java.util.ArrayList<String>
            )
            startActivity(intent)
        }
    }
}