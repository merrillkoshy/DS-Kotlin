package diplomatssummit.com.diplomatssummit.Networking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import okhttp3.Call
import retrofit2.Retrofit

import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.POST
import retrofit2.http.Multipart
import retrofit2.http.Part


internal interface Service {
    @Multipart
    @POST("/")
    fun postImage(@Part image: MultipartBody.Part, @Part("name") name: RequestBody): Call<ResponseBody>
}
