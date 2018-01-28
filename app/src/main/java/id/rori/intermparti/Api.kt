package id.rori.intermparti

import id.rori.intermparti.model.ForcastResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by trozan on 27/01/18.
 */

interface Api{
    @GET("forecast?id=1621177&appid=91ffcf633b4369135adf753d37304ba6")
    fun getForcast() : Call<ForcastResponse>
}