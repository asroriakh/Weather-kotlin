package id.rori.intermparti

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by trozan on 28/01/18.
 */

fun convertDateToWeekDay(date: String?): String?{
    val oldSdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val oldDate : Date = oldSdf.parse(date)

    val daySdf = SimpleDateFormat("EEEE", Locale("id"))
    val day = daySdf.format(oldDate)

    println(day)
    return day
}

fun getIcon(weatherId: Int):Int{
    when(weatherId){
        in 200..232 -> return R.drawable.ic_storm
        in 300..321 -> return R.drawable.ic_light_rain
        in 500..504 -> return R.drawable.ic_rain
        511         -> return R.drawable.ic_snow
        in 520..531 -> return R.drawable.ic_rain
        in 600..622 -> return R.drawable.ic_snow
        in 701..761 -> return R.drawable.ic_fog
        761,771,781 -> return R.drawable.ic_storm
        800         -> return R.drawable.ic_clear
        801         -> return R.drawable.ic_light_clouds
        in 802..804 -> return R.drawable.ic_cloudy
        in 900..906 -> return R.drawable.ic_storm
        in 958..962 -> return R.drawable.ic_storm
        in 951..957 -> return R.drawable.ic_clear
        else        -> return R.drawable.ic_storm
    }
}