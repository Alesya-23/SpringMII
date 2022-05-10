package com.lab.mii

import com.github.mikephil.charting.data.Entry

data class ClusterDataPeople(
    val point: Entry,
    val affiliation: Array<Double>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ClusterDataPeople

        if (point != other.point) return false
        if (!affiliation.contentEquals(other.affiliation)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = point.hashCode()
        result = 31 * result + affiliation.contentHashCode()
        return result
    }
}