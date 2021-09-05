package id.viharaumat.FileUtama

import retrofit.Callback
import retrofit.client.Response
import retrofit.http.*
import retrofit.mime.TypedFile

interface InterfaceRetro {

    @FormUrlEncoded
    @POST("/vdd_login.php")
    fun vdd_login(
        @Field("kunci") kunci: String,
        @Field("email") email: String,
        @Field("password") password: String,
        callback: Callback<Response>
    )

    @Multipart
    @POST("/umatubahprofil2.php")
    fun umatubahprofil2(
        @Part("kunci") kunci: String,
        @Part("uuid_umat") uuid_umat: String,
        @Part("nama") nama: String,
        @Part("ponsel") ponsel: String,
        @Part("vaksin") vaksin: String,
        @Part("alamat") alamat: String,
        @Part("kota") kota: String,
        @Part("kerabat") kerabat: String,
        @Part("hubungan") hubungan: String,
        @Part("phone_kerabat") phone_kerabat: String,
        @Part("image") image: TypedFile,
        callback: Callback<Response>
    )

}
