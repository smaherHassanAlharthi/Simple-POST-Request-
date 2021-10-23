package com.example.simplepostrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var nameED: EditText
    private lateinit var button: Button
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameED= findViewById(R.id.nameED)
        button= findViewById(R.id.button)
        status= findViewById(R.id.status)

        button.setOnClickListener{
            if (nameED.text.isNotBlank()){
                val apiInterface= APIClient().getClient()?.create(APIInterface::class.java)
                apiInterface?.addCelebrity(Name(nameED.text.toString()))?.enqueue(object: Callback<Name>{
                    override fun onResponse(call: Call<Name>, response: Response<Name>) {
                        status.text= "Add Successfully"
                    }

                    override fun onFailure(call: Call<Name>, t: Throwable) {
                        status.text= "Something Went Wrong"
                    }

                })
            }
            else{
                status.text= "Please Enter Name"
            }
            nameED.text.clear()
        }



    }
}