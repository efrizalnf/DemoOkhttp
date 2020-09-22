package id.zlz.demookhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        okHtttpCall()
    }

    private fun okHtttpCall() {
        val url = "https://reqres.in/api/users?page=2"
        val req = Request.Builder()
            .url(url)
            .build()
        val client = OkHttpClient()

        client.newCall(req).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    val mResponse: String? = response.body?.string()

                    this@MainActivity.runOnUiThread(
                        Runnable {
                            message.text =mResponse
                        }
                    )
                }
            }
        })
    }
}