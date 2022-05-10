package com.lab.mii

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.lab.mii.databinding.ActivityLabaTwoBinding
import kotlin.math.pow


class LabTwoActivity : AppCompatActivity() {
    private var labTwo = LabTwo()
    private lateinit var chart: ScatterChart
    private var isPointsGenerate = false
    private lateinit var labTwoBinding: ActivityLabaTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laba_two)
        labTwoBinding = ActivityLabaTwoBinding.inflate(layoutInflater)
        val view = labTwoBinding.root
        setContentView(view)
        chart = labTwoBinding.chart
        clickButton()
    }

    private fun clickButton() {
        labTwoBinding.buttonWorkAlgorithm.setOnClickListener {
            val countCluster = labTwoBinding.inputCountCluster.text.toString()
            if (countCluster.isNotEmpty() && !isPointsGenerate) {
                labTwo.randDataForCluster(countCluster.toInt())
                isPointsGenerate = true
            }
            if (isPointsGenerate) {
                labTwo.oneIterationFcm()
                drawGraph()
            }
        }
    }


    private fun drawGraph() {
        chart.clear()
        val set1 = ScatterDataSet(labTwo.getPointsPeople(), "points")
        set1.setScatterShape(ScatterChart.ScatterShape.SQUARE)
        set1.color = ColorTemplate.COLORFUL_COLORS[0]
        val set2 = ScatterDataSet(labTwo.clustersCentre, "centerClusters")
        set2.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
        set2.scatterShapeHoleColor = ColorTemplate.COLORFUL_COLORS[3]
        set2.scatterShapeHoleRadius = 3f
        set2.color = ColorTemplate.COLORFUL_COLORS[1]
        set1.scatterShapeSize = 8f
        set2.scatterShapeSize = 8f
        val dataSets: ArrayList<IScatterDataSet> = ArrayList()
        dataSets.add(set1)
        dataSets.add(set2)
        drawCurcle(dataSets)
        val data = ScatterData(dataSets)
        chart.data = data
        chart.invalidate()
    }

    private fun drawCurcle(dataSets: ArrayList<IScatterDataSet>) {
        //  val dataSets: ArrayList<ScatterDataSet> = ArrayList()
        for (i in 0 until labTwo.countCluster) {
            val set1 = ScatterDataSet(labTwo.getPointsPeople(), "$i clusters")
            set1.color = ColorTemplate.COLORFUL_COLORS[i]
            set1.setScatterShape(ScatterChart.ScatterShape.CIRCLE)
            set1.scatterShapeHoleRadius = 1f
            set1.scatterShapeSize = 1f
            val radius = kotlin.math.sqrt(
                (labTwo.peoplesFarClusters[i].x - labTwo.clustersCentre[i].x).pow(2) +
                        (labTwo.peoplesFarClusters[i].y - labTwo.clustersCentre[i].y).pow(2)
            ).toDouble()
            for (j in 0 until 360) {
                val newX =
                    labTwo.clustersCentre[i].x + radius * kotlin.math.cos(j.toDouble() * Math.PI / 180)
                val newY =
                    labTwo.clustersCentre[i].y + radius * kotlin.math.sin(j.toDouble() * Math.PI / 180)
                set1.addEntry(Entry(newX.toFloat(), newY.toFloat()))
            }
            dataSets.add(set1)
        }
    }
}