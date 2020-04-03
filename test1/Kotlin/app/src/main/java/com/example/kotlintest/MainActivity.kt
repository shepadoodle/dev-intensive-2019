package com.example.kotlintest

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException
import org.json.JSONObject



class MainActivity : AppCompatActivity() {
    private val PRODUCT_LIST_URL = "https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/list2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val task = DownloadJSONTask()

        task.execute(PRODUCT_LIST_URL)
    }

    private class DownloadJSONTask : AsyncTask<String?, Void?, String?>() {

        override fun doInBackground(vararg params: String?): String? {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onPostExecute(s: String?) {
            super.onPostExecute(s)
            try {
                val jsonObject = JSONObject(s)
                val jsonArray = jsonObject.getJSONArray("weather")
                val weather = jsonArray.getJSONObject(0)
                val main = weather.getString("main")
                val description = weather.getString("description")
                Log.i("MyResult", "$main $description")
            } catch (e: JSONException) {
                Log.e("", "", e);
            }
        }
    }
}
