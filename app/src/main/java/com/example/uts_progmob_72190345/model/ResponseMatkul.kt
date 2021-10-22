package com.example.uts_progmob_72190345.model

import com.google.gson.annotations.SerializedName

data class ResponseMatkul(

	@field:SerializedName("ResponseMatkul")
	val responseMatkul: List<ResponseMatkulItem?>? = null
)

data class ResponseMatkulItem(

	@field:SerializedName("hari")
	var hari: String? = null,

	@field:SerializedName("nama")
	var nama: String? = null,

	@field:SerializedName("kode")
	var kode: String? = null,

	@field:SerializedName("sks")
	var sks: String? = null,

	@field:SerializedName("id")
	var id: String? = null,

	@field:SerializedName("sesi")
	var sesi: String? = null,

	@field:SerializedName("nim_progmob")
	var nim_progmob: String? = null,

	@field:SerializedName("kode_cari")
	var kode_cari: String? = null
)
