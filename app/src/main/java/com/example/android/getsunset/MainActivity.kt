package com.example.android.getsunset

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun GetSunsetWheather(view:View){
        var url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22${TextUtils.htmlEncode(etCityName.text.toString())})&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys"

    }

    inner class RequestWheather() : AsyncTask<String, String, String>(){

        override fun doInBackground(vararg params: String?): String {
            var inString:String = ""
           try {
               if (params.isNotEmpty()){
                   var url = URL(params[0])
                   val urlConnect = url.openConnection() as HttpURLConnection
                   urlConnect.connectTimeout = 7000

                   inString = ConvertStreamToString(urlConnect.inputStream)
                   //publishProgress(inString)
               }
           }catch (ex:Exception){

           }
            return inString
        }

        private fun ConvertStreamToString(stream: InputStream?): String {
            val bufferedReader = BufferedReader(InputStreamReader(stream))
            var line:String
            var allString:String = ""

            do{
                line = bufferedReader.readLine()
                if (line != null){
                    allString += line
                }
            }while (line != null)
            stream!!.close()
            return allString
        }

        override fun onPreExecute() {
            //before task started
        }

        override fun onPostExecute(result: String?) {
            try {

            }catch (ex:Exception){

            }
        }

        override fun onProgressUpdate(vararg values: String?) {
            //
        }

    }
}
