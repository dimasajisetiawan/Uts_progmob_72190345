package com.example.uts_progmob_72190345.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uts_progmob_72190345.R
import com.example.uts_progmob_72190345.model.ResponseAddMatkul
import com.example.uts_progmob_72190345.model.ResponseMatkulItem
import com.example.uts_progmob_72190345.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMatkulActivity : AppCompatActivity() {
    lateinit var edKode : EditText
    lateinit var edNama : EditText
    lateinit var edHari : EditText
    lateinit var edSesi : EditText
    lateinit var edSks : EditText
    lateinit var edNimProgmob : EditText
    lateinit var btnSimpanMatkul : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_matkul)
        edKode = findViewById(R.id.edKode)
        edNama = findViewById(R.id.edNama)
        edHari = findViewById(R.id.edHari)
        edSesi = findViewById(R.id.edSesi)
        edSks = findViewById(R.id.edSks)
        edNimProgmob = findViewById(R.id.edNim_progmob)
        btnSimpanMatkul = findViewById(R.id.btnSimpanMatkul)

        btnSimpanMatkul.setOnClickListener(View.OnClickListener { view ->
            var ptn = ResponseMatkulItem()
            ptn.kode = edKode.text.toString()
            ptn.nama = edNama.text.toString()
            ptn.hari = edHari.text.toString()
            ptn.sesi = edSesi.text.toString()
            ptn.sks = edSks.text.toString()
            ptn.nim_progmob = edNimProgmob.text.toString()
            ptn.kode_cari = edKode.text.toString()
            ptn.id = null

            NetworkConfig().getService()
                .addMatkul(ptn)
                .enqueue(object : Callback<ResponseAddMatkul?> {
                    override fun onFailure(call: Call<ResponseAddMatkul?>, t:
                    Throwable) {
                        Toast.makeText(this@AddMatkulActivity, t.localizedMessage,
                            Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(
                        call: Call<ResponseAddMatkul?>,
                        response: Response<ResponseAddMatkul?>
                    ) {
                        Toast.makeText(this@AddMatkulActivity,"Berhasil Tambah Data", Toast.LENGTH_LONG).show()
                    }
                })
        })
    }
}