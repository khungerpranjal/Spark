package com.demo.sparklive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.sparklive.adapter.GridAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_list.layoutManager = GridLayoutManager(this,2)
        getData()
    }

    private fun loadJSONFromAsset(): String? {
        //function to load the JSON from the Asset and return the object
        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val `is` = assets.open("video_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun getData() {

        try {
            // since we hav e JSON object, so we are getting the object
            //here we are calling a function and that function is returning the JSON object
            val obj = JSONObject(loadJSONFromAsset())
            val results = obj.getJSONArray("results")

            rv_list.adapter = GridAdapter(results, this@MainActivity)
        } catch (e: JSONException) {
            //exception
            e.printStackTrace()
        }

    }


}
