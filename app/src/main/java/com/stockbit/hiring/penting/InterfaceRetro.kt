package id.viharaumat.FileUtama

import retrofit.Callback
import retrofit.client.Response
import retrofit.http.*
import retrofit.mime.TypedFile

interface InterfaceRetro {

    @GET("/totaltoptiervolfull")
    fun totaltoptiervolfull(
        @Query("limit") limit: String,
        @Query("tsym") tsym: String,
        callback: Callback<Response>
    )

}
