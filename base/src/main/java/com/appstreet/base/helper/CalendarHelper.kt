package com.appstreet.base.helper

import android.util.Log
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
import java.util.*
import java.text.ParseException


class CalendarHelper {

    companion object{
        fun getCurrentDate(): LocalDate? {
            var c = Calendar.getInstance().getTime()
            var formatter = SimpleDateFormat("yyyy-MM-dd")
            val todayString = formatter.format(c)
            val dateArray = todayString.split("-")
            return LocalDate.of(dateArray[0].toInt(), dateArray[1].toInt(), dateArray[2].toInt())
        }

        fun getFirstDateOfWeek(): String{
            val c = Calendar.getInstance()
            var formatter = SimpleDateFormat("dd MMM yyyy")
            c.set(Calendar.DAY_OF_WEEK, 1)

            Log.d("MK_DATE3", ""+c)

            val year1 = c.get(Calendar.YEAR)
            val month1 = c.get(Calendar.MONTH) + 1
            val day1 = c.get(Calendar.DAY_OF_MONTH)

            Log.d("MK_DATE2", ""+year1 +", "+month1 +", "+day1)

            val tempDate = ""+year1 +"-" +month1 +"-" +day1
            val dateFormatprev = SimpleDateFormat("yyyy-MM-dd")
            val d = dateFormatprev.parse(tempDate)
            val date = formatter.format(d)

            return date
        }

        fun getLastDateOfWeek(): String{
            val c = Calendar.getInstance()
            var formatter = SimpleDateFormat("dd MMM yyyy")
            c.set(Calendar.DAY_OF_WEEK, 7)

            Log.d("MK_DATE3", ""+c)

            val year1 = c.get(Calendar.YEAR)
            val month1 = c.get(Calendar.MONTH) + 1
            val day1 = c.get(Calendar.DAY_OF_MONTH)

            Log.d("MK_DATE2", ""+year1 +", "+month1 +", "+day1)

            val tempDate = ""+year1 +"-" +month1 +"-" +day1
            val dateFormatprev = SimpleDateFormat("yyyy-MM-dd")
            val d = dateFormatprev.parse(tempDate)
            val date = formatter.format(d)

            return date
        }


        fun getNextDate(selectedDate: LocalDate?): LocalDate{
            val dt = selectedDate.toString()  // Start date
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val c = Calendar.getInstance()
            try {
                c.time = sdf.parse(dt)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            c.add(
                Calendar.DATE, +1
            )  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            val sdf1 = SimpleDateFormat("yyyy-MM-dd")
            val output = sdf1.format(c.time)
            val dateArray = output.split("-")
            return LocalDate.of(dateArray[0].toInt(), dateArray[1].toInt(), dateArray[2].toInt())
        }

        fun getPreviousDate(selectedDate: LocalDate?): LocalDate{
            val dt = selectedDate.toString()  // Start date
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val c = Calendar.getInstance()
            try {
                c.time = sdf.parse(dt)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            c.add(
                Calendar.DATE, -1
            )  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
            val sdf1 = SimpleDateFormat("yyyy-MM-dd")
            val output = sdf1.format(c.time)
            val dateArray = output.split("-")
            return LocalDate.of(dateArray[0].toInt(), dateArray[1].toInt(), dateArray[2].toInt())
        }

    }
}