package com.lock.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lock.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        fetchdata()
    }

    private fun fetchdata() {
        val retrofitbuilder= Retrofit.Builder()
            .addCallAdapterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(Apiinteface::class.java)
        val retrofitdata= retrofitbuilder.getData()

        retrofitdata.enqueue(object : Callback<List<DataItemModel>?> {
            override fun onResponse(
                call: Call<List<DataItemModel>?>,
                response: Response<List<DataItemModel>?>
            ) {
                val resposnsebody= response.body()!!
                val mystringbuilder=StringBuilder()

                for(mydata in resposnsebody){
                    mystringbuilder.append(mydata.id)
                    mystringbuilder.append("/n")
                }
                binding.textView.text=mystringbuilder
            }

            override fun onFailure(call: Call<List<DataItemModel>?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"$(t.message)",Toast.LENGTH_LONG).show()
            }

        })
    }
}