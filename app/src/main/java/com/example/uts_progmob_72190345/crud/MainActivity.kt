package com.example.uts_progmob_72190345.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_progmob_72190345.R
import com.example.uts_progmob_72190345.adapter.ResponseMatkulAdapter
import com.example.uts_progmob_72190345.model.ResponseMatkul
import com.example.uts_progmob_72190345.model.ResponseMatkulItem
import com.example.uts_progmob_72190345.network.NetworkConfig
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var rvGetMatkul : RecyclerView
        lateinit var fabAddMatkul : FloatingActionButton
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvGetMatkul = findViewById(R.id.rvListMatkul)
        fabAddMatkul = findViewById(R.id.fabAddMatkul)
        NetworkConfig().getService()
            .getMatkulNim("72190345")
            .enqueue(object : Callback<List<ResponseMatkulItem>> {
                override fun onFailure(call: Call<List<ResponseMatkulItem>>, t:
                Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage,
                        Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<ResponseMatkulItem>>,
                    response: Response<List<ResponseMatkulItem>>
                ) {
                    rvGetMatkul.apply{
                        layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = ResponseMatkulAdapter(response.body())
                    }
                }
            })
        fabAddMatkul.setOnClickListener(View.OnClickListener{ view ->
            var intent = Intent(this@MainActivity,AddMatkulActivity::class.java)
            startActivity(intent)
        })
    }
}