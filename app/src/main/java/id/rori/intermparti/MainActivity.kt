package id.rori.intermparti

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.i
import com.google.gson.Gson
import id.rori.intermparti.model.Forcast
import id.rori.intermparti.model.ForcastResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val tag = this::class.java.simpleName
    private lateinit var adapter: WeatherAdapter

    private val forcastList = mutableListOf<Forcast>()

//    http://samples.openweathermap.org/data/2.5/forecast?id=1621177&appid=b1b15e88fa797225412429c1c50c122a1
//    08c04552ddb079dce6b88e506059219f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        setupView()
    }

    private fun setupView(){
        adapter = WeatherAdapter(forcastList)
        recCuaca.layoutManager = LinearLayoutManager(this)
        recCuaca.adapter = adapter
    }

    private fun getData(){
        App.api.getForcast().enqueue(object : Callback<ForcastResponse>{

            override fun onResponse(call: Call<ForcastResponse>?, response: Response<ForcastResponse>?) {
                i(tag, "data : ${Gson().toJsonTree(response?.body())}")
                val forcastResponse: ForcastResponse? = response?.body()

                val kota = forcastResponse?.city?.name
                val kodeNegara = forcastResponse?.city?.country?.toUpperCase()
                tvKota.text = "$kota,$kodeNegara"
                val nForcastList = response?.body()?.forcastList
                nForcastList?.map {
                    it.dtTxt = convertDateToWeekDay(it.dtTxt)
                }
                val newForcastList = nForcastList?.distinctBy { it.dtTxt }
                displayCurForcast(newForcastList?.get(0))
                newForcastList?.let {
                    forcastList.addAll(it)
                    forcastList.removeAt(0)
                    adapter.notifyDataSetChanged()
                }
            }
            override  fun onFailure(call: Call<ForcastResponse>?, t: Throwable){
                i(tag, "data : ${Gson().toJsonTree(t?.message)}")
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun displayCurForcast(forcast: Forcast?){
        tvHariIni.text = "Hari Ini"
        tvDerajatSuhu.text = forcast?.main?.temp?.toDouble()
                ?.toInt()?.minus(273)?.toString()
        val icon = getIcon(forcast?.weather?.get(0)?.id?.toInt()?:0)
        imgStatus.setImageDrawable(
                ContextCompat.getDrawable(this,icon)
        )
        tvStatus.text = forcast?.weather?.get(0)?.description
    }

}
