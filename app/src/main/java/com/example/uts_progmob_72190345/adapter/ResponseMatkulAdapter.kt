package com.example.uts_progmob_72190345.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.uts_progmob_72190345.R
import com.example.uts_progmob_72190345.crud.MainActivity
import com.example.uts_progmob_72190345.crud.UpdateMatkulActivity
import com.example.uts_progmob_72190345.model.ResponseAddMatkul
import com.example.uts_progmob_72190345.model.ResponseMatkulItem
import com.example.uts_progmob_72190345.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseMatkulAdapter (val Matkul: List<ResponseMatkulItem>?):
    RecyclerView.Adapter<ResponseMatkulAdapter.ResponseMatkulHolder>() {
    lateinit var mContext : Context
    lateinit var adapter: ResponseMatkulAdapter
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ResponseMatkulAdapter.ResponseMatkulHolder { return ResponseMatkulHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.rv_item_matkul, parent, false))
    }
    override fun onBindViewHolder(holder: ResponseMatkulAdapter.ResponseMatkulHolder, position: Int) {
        holder.bindMatkul(Matkul?.get(position))
        var popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
        popupMenu.menu.add(Menu.NONE, 0, 0, "Edit")
        popupMenu.menu.add(Menu.NONE, 1, 1, "Delete")
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            mContext = holder.itemView.context
            if (id == 0) {
                val bundle = Bundle()
                var kodeCariTmp = Matkul?.get(position)?.kode_cari.toString()
                var kodeTmp = Matkul?.get(position)?.kode.toString()
                var namaTmp = Matkul?.get(position)?.nama.toString()
                var hariTmp = Matkul?.get(position)?.hari.toString()
                var sesiTmp = Matkul?.get(position)?.sesi.toString()
                var sksTmp = Matkul?.get(position)?.sks.toString()
                var nim_progmobTmp = Matkul?.get(position)?.nim_progmob.toString()
                bundle.putString("kodeCariTmp", kodeCariTmp)
                bundle.putString("kodeTmp",kodeTmp)
                bundle.putString("namaTmp",namaTmp)
                bundle.putString("hariTmp",hariTmp)
                bundle.putString("sesiTmp",sesiTmp)
                bundle.putString("sksTmp",sksTmp)
                bundle.putString("nimProgmobTmp",nim_progmobTmp)
                var intent = Intent(mContext, UpdateMatkulActivity::class.java)
                intent.putExtras(bundle)
                mContext.startActivity(intent)
            } else if (id == 1) {
                var ptn = ResponseMatkulItem()
                ptn.kode = Matkul?.get(position)?.kode.toString()
                ptn.nim_progmob = Matkul?.get(position)?.nim_progmob.toString()
                NetworkConfig().getService()
                    .deleteMatkul(ptn)
                    .enqueue(object : Callback<ResponseAddMatkul?> {
                        override fun onFailure(call: Call<ResponseAddMatkul?>, t: Throwable) {
                            Toast.makeText(
                                holder.itemView.context,
                                t.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<ResponseAddMatkul?>,
                            response: Response<ResponseAddMatkul?>
                        ) {
                            Toast.makeText(
                                holder.itemView.context,
                                "Berhasil hapus data",
                                Toast.LENGTH_SHORT
                            ).show()
                            (mContext as MainActivity).finish()
                            var intent = Intent(mContext, MainActivity::class.java)
                            mContext.startActivity(intent)
                        }
                    })
            }
            false
        }
        holder.itemView.setOnClickListener( { view ->
            popupMenu.show()
        })
    }


    override fun getItemCount(): Int {
        return Matkul?.size ?: 0
    }
    class ResponseMatkulHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtId: TextView
        lateinit var txtKode: TextView
        lateinit var txtNama: TextView
        lateinit var txtHari: TextView
        lateinit var  txtSesi : TextView
        lateinit var txtSks : TextView
        fun bindMatkul(Matkul: ResponseMatkulItem?) {
            itemView.apply {
                txtId = findViewById(R.id.id)
                txtKode = findViewById(R.id.kode)
                txtNama = findViewById(R.id.nama)
                txtHari = findViewById(R.id.hari)
                txtSesi = findViewById(R.id.sesi)
                txtSks = findViewById(R.id.sks)
                txtId.text = Matkul?.id
                txtKode.text = Matkul?.kode
                txtNama.text = Matkul?.nama
                txtHari.text = Matkul?.hari
                txtSesi.text = Matkul?.sesi
                txtSks.text = Matkul?.sks
            }
        }
    }
}
