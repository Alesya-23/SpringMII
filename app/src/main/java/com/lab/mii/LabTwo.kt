package com.lab.mii

import com.github.mikephil.charting.data.Entry
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

class LabTwo {
    private val n: Int = 100
    private val m: Int = 2
    private val e: Double = 0.001
    var u = 1
    private val countIteration = 100
    var countCluster by Delegates.notNull<Int>()
    private var peoples: ArrayList<ClusterDataPeople> = ArrayList()
    var peoplesFarClusters: ArrayList<Entry> = ArrayList()
    var clustersCentre: ArrayList<Entry> = ArrayList()
    private var valueMinimizedFunctionLast = 0.0
    private var valueMinimizedFunctionNow = 0.0
    private var finish = false

    fun oneIterationFcm() {
        for (i in 0 until n) {
            for (j in 0 until countCluster) {
                valueMinimizedFunctionLast += (peoples[i].affiliation[j]).pow(m) *
                        sqrt(
                            (peoples[i].point.x - clustersCentre[j].x).pow(2) +
                                    (peoples[i].point.y - clustersCentre[j].y).pow(2)
                        )
            }
        }
        calculateCenterCluster()
        calculateDegreeMatrixAffiliation()
        minimizedFunction()
        findFarPoints()
    }

    private fun minimizedFunction() {
        for (i in 0 until n) {
            for (j in 0 until countCluster) {
                valueMinimizedFunctionNow += (peoples[i].affiliation[j]).pow(m) *
                        sqrt(
                            (peoples[i].point.x - clustersCentre[j].x).pow(2) +
                                    (peoples[i].point.y - clustersCentre[j].y).pow(2)
                        )
            }
        }
        if (abs(valueMinimizedFunctionNow - valueMinimizedFunctionLast) <= e) {
            finish = true
        }
    }

    private fun calculateCenterCluster() {
        var numeratorX = 1.0
        var numeratorY = 1.0
        var denominator = 1.0
        for (i in 0 until countCluster) {
            for (j in 0 until n) {
                numeratorX += (peoples[j].affiliation[i]).pow(1.6) * peoples[j].point.x
                denominator += (peoples[j].affiliation[i]).pow(1.6)
            }
            clustersCentre[i].x = (numeratorX / denominator).toFloat()
        }
    }

    private fun calculateSum(i: Int, j: Int): Double {
        var sum = 0.0
        for (l in 0 until countCluster) {
            sum += (sqrt(
                (peoples[i].point.x - clustersCentre[j].x).pow(2) +
                        (peoples[i].point.y - clustersCentre[j].y).pow(2)
            ) /
                    sqrt(
                        (peoples[i].point.x - clustersCentre[l].x).pow(2) +
                                (peoples[i].point.y - clustersCentre[l].y).pow(2)
                    )).toDouble().pow(3.33)
        }
        return sum
    }

    private fun calculateDegreeMatrixAffiliation() {
        for (i in 0 until n) {
            for (j in 0 until countCluster) {
                peoples[i].affiliation[j] = 1 / calculateSum(i, j)
            }
        }
    }

    private fun findFarPoints() {
        peoplesFarClusters.clear()
        peoplesFarClusters = ArrayList(countCluster)
        for (i in 0 until countCluster) {
            var diameterLast = 0.0
            var diameterNow: Double
            val entry = Entry()
            for (j in 0 until n) {
                diameterNow = sqrt(
                    (peoples[j].point.x - clustersCentre[i].x).pow(2) +
                            (peoples[j].point.y - clustersCentre[i].y).pow(2)
                ).toDouble()
                if (diameterNow > diameterLast && peoples[j].affiliation[i] <= 0.5) {
                    diameterLast = diameterNow
                    entry.x = peoples[j].point.x
                    entry.y = peoples[j].point.y
                }
            }
            peoplesFarClusters.add(Entry(entry.x, entry.y))
        }
    }

    private fun randPoints() {
        for (i in 0 until n) {
            val age = Random.nextInt(14..100)
            val height = Random.nextInt(120..200)
            val affiliation = Array(countCluster) { Random.nextDouble(0.0, 1.0) }
            peoples.add(ClusterDataPeople(Entry(age.toFloat(), height.toFloat()), affiliation))
        }
    }

    private fun randClustersCentre() {
        for (i in 0 until countCluster) {
            val age = Random.nextInt(0..100)
            val height = Random.nextInt(120..200)
            clustersCentre.add(Entry(age.toFloat(), height.toFloat()))
        }
    }

    fun randDataForCluster(countClusters: Int) {
        countCluster = countClusters
        randClustersCentre()
        randPoints()
    }

    fun getPointsPeople(): ArrayList<Entry> {
        val listPoints = ArrayList<Entry>()
        for (i in 0 until n)
            listPoints.add(peoples[i].point)
        return listPoints
    }
}