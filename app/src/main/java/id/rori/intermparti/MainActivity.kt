package id.rori.intermparti

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    http://samples.openweathermap.org/data/2.5/forecast?id=1621177&appid=b1b15e88fa797225412429c1c50c122a1
//    08c04552ddb079dce6b88e506059219f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvTes.text = "aisjfian"
    }
}
