package com.lab.mii

import com.github.mikephil.charting.data.Entry

class LabThree {
    var entries: ArrayList<Entry> = ArrayList()
    private var entryWithAffiliation: ArrayList<MyEntry> = ArrayList()
    var aParam: Int? = null
    var bParam: Int? = null
    var cParam: Int? = null
    var dParam: Int? = null

    fun addPoint(xParameterValue: String) {
        if (xParameterValue.isNotEmpty()) {
            val yParameter = getY(xParameterValue.toFloat())
            entries.add(Entry(xParameterValue.toFloat(), yParameter))
            entries = gnomeSort(entries)
        }
    }

    private fun getY(point: Float): Float {
        var y = 0f
        if (point <= aParam!!) {
            y = 0f
            entryWithAffiliation.add(MyEntry(Entry(point, y), "низкий"))
            return y
        } else if (point >= aParam!! && point <= bParam!!) {
            y = (point - aParam!!) / (bParam!! - aParam!!)
            entryWithAffiliation.add(MyEntry(Entry(point, y), "нормальный"))
            return y
        } else if (point >= bParam!! && point <= cParam!!) {
            y = 1f
            entryWithAffiliation.add(MyEntry(Entry(point, y), "высокий"))
            return y
        } else if (point >= cParam!! && point <= dParam!!) {
            y = (dParam!! - point) / (dParam!! - cParam!!)
            entryWithAffiliation.add(MyEntry(Entry(point, y), "низкий"))
            return y
        } else {
            y = 0f
            entryWithAffiliation.add(MyEntry(Entry(point, y), "очень низкий"))
            return y
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