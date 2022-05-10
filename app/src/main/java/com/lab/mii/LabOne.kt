package com.lab.mii

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.lab.mii.databinding.ActivityLabOneBinding

class LabOne {
    private var entries: ArrayList<Entry> = ArrayList()
    private var entriesOne: ArrayList<Entry> = ArrayList()
    private var entriesTwo: ArrayList<Entry> = ArrayList()
    private var entriesThree: ArrayList<Entry> = ArrayList()
    private var entriesFour: ArrayList<Entry> = ArrayList()
    private var entriesFive: ArrayList<Entry> = ArrayList()
    var aParam: Int? = null
    var bParam: Int? = null
    var cParam: Int? = null
    var dParam: Int? = null

    fun addPoint(chart: LineChart, activityBinding: ActivityLabOneBinding) {
        val xParameter = activityBinding.xCoordinateInput.text.toString()
        if (xParameter.isNotEmpty()) {
            val yParameter = getY(xParameter.toFloat())
            entries.add(Entry(xParameter.toFloat(), yParameter))
            entries = gnomeSort(entries)
            val dataSet = LineDataSet(entries, "ТФП")
            val dataSetOne = LineDataSet(entriesOne, "x <=a")
            val dataSetTwo = LineDataSet(entriesTwo, "\nx >= a && x <= b")
            val dataSetThree = LineDataSet(entriesThree, "x >= b && x <= c")
            val dataSetFour = LineDataSet(entriesFour, "x >= c && x <=d")
            val dataSetFive = LineDataSet(entriesFive, "x >= d")
            // Линии графиков соберем в один массив
            dataSet.setDrawFilled(true)
            dataSetOne.color = Color.MAGENTA
            dataSetTwo.color = Color.RED
            dataSetThree.color = Color.YELLOW
            dataSetFour.color = Color.GREEN
            dataSetFive.color = Color.BLUE
            val dataSets: ArrayList<ILineDataSet> = ArrayList()
            dataSets.add(dataSet)
            dataSets.add(dataSetOne)
            dataSets.add(dataSetTwo)
            dataSets.add(dataSetThree)
            dataSets.add(dataSetFour)
            dataSets.add(dataSetFive)
            // Создадим переменную  данных для графика
            val data = LineData(dataSets)
            chart.data = data
            // Не забудем отправить команду на перерисовку кадра, иначе график не отобразится
            chart.invalidate()
        }
        activityBinding.xCoordinateInput.text.clear()
    }


    private fun getY(point: Float): Float {
        if (point <= aParam!!) {
            entriesOne.add(Entry(point, 0f))
            entriesOne = gnomeSort(entriesOne)
            return 0f
        } else if (point >= aParam!! && point <= bParam!!) {
            entriesTwo.add(Entry(point, (point - aParam!!) / (bParam!! - aParam!!)))
            entriesTwo = gnomeSort(entriesTwo)
            return (point - aParam!!) / (bParam!! - aParam!!)
        } else if (point >= bParam!! && point <= cParam!!) {
            entriesThree.add(Entry(point, 1f))
            entriesThree = gnomeSort(entriesThree)
            return 1f
        } else if (point >= cParam!! && point <= dParam!!) {
            entriesFour.add(Entry(point, (dParam!! - point) / (dParam!! - cParam!!)))
            entriesFour = gnomeSort(entriesFour)
            return (dParam!! - point) / (dParam!! - cParam!!)
        } else {
            entriesFive.add(Entry(point, 0f))
            entriesFive = gnomeSort(entriesFive)
            return 0f
        }
    }

    private fun gnomeSort(a: ArrayList<Entry>, ascending: Boolean = true): ArrayList<Entry> {
        var i = 1
        var j = 2
        while (i < a.size) {
            if (ascending && (a[i - 1].x <= a[i].x) ||
                !ascending && (a[i - 1].x >= a[i].x)
            )
                i = j++
            else {
                val temp = a[i - 1]
                a[i - 1] = a[i]
                a[i--] = temp
                if (i == 0) i = j++
            }
        }
        return a
    }
}