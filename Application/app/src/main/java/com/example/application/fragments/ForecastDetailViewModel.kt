package com.example.application.fragments

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.androidplot.xy.CatmullRomInterpolator
import com.androidplot.xy.LineAndPointFormatter
import com.androidplot.xy.PanZoom
import com.androidplot.xy.SimpleXYSeries
import com.androidplot.xy.XYGraphWidget
import com.androidplot.xy.XYPlot
import com.androidplot.xy.XYSeries
import com.example.application.entities.ForecastResponse
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Arrays

class ForecastDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    fun unixToText(unixTime: Long, strDateFormat: String, timezone: String): String {
        val instant = Instant.ofEpochSecond(unixTime)
        val timezoneObj = ZoneId.of(timezone)
        val timezoneDate = ZonedDateTime.ofInstant(instant, timezoneObj)
        val formato = DateTimeFormatter.ofPattern(strDateFormat)
        return timezoneDate.format(formato)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setUpTempPlot(forecast: ForecastResponse, plot: XYPlot){
        val domainLabels = mutableListOf<Number>()
        val series1Number = mutableListOf<Number>()
        var i=0
        for(hour in forecast.forecast.forecastDays[0].hour){
            series1Number.add(hour.tempC)
            domainLabels.add(i)
            i+=1
        }

        val series1 : XYSeries = SimpleXYSeries(
            series1Number,
            SimpleXYSeries.ArrayFormat.Y_VALS_ONLY
            ,"Temperatura")


        val series1Format = LineAndPointFormatter(Color.RED, Color.BLACK,null,null)

        series1Format.setInterpolationParams(
            CatmullRomInterpolator.Params(10,
                CatmullRomInterpolator.Type.Centripetal))


        plot.addSeries(series1,series1Format)


        plot.graph.getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).format = object : Format() {
            override fun format(
                obj: Any?,
                toAppendTo: StringBuffer,
                pos: FieldPosition
            ): StringBuffer {
                val i = Math.round((obj as Number).toFloat())
                return toAppendTo.append(domainLabels[i])
            }

            override fun parseObject(source: String?, pos: ParsePosition): Any? {
                return null
            }

        }
        PanZoom.attach(plot)
    }
}