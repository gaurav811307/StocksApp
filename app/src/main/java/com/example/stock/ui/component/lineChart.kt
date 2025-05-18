package com.example.stock.ui.component


import android.graphics.Color
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LineChart(data: List<Float>) {
 //   AndroidView(
//        factory = { context ->
//            MPLineChart(context).apply {
//                layoutParams = ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    400
//                )
//                xAxis.position = XAxis.XAxisPosition.BOTTOM
//                axisRight.isEnabled = false
//                description.isEnabled = false
//                legend.isEnabled = false
//                animateX(800)
//            }
//        },
//        update = { chart ->
//            val entries = data.mapIndexed { index, value ->
//                Entry(index.toFloat(), value)
//            }
//
//            val lineDataSet = LineDataSet(entries, "Price").apply {
//                color = Color.BLUE
//                valueTextColor = Color.BLACK
//                lineWidth = 2f
//                setDrawCircles(false)
//                setDrawValues(false)
//                mode = LineDataSet.Mode.CUBIC_BEZIER
//            }
//
//            chart.data = LineData(lineDataSet)
//            chart.invalidate()
//        }
 //   )
}
