// package diplomatssummit.com.diplomatssummit.Networking
//
// import android.os.Bundle
// import android.support.v7.app.AppCompatActivity
// import okhttp3.*
// import retrofit2.Call
// import retrofit2.http.*
// import okhttp3.logging.HttpLoggingInterceptor
// import retrofit2.Retrofit
// import java.io.File
//
//
// class ApiConfig:AppCompatActivity() {
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// val interceptor = HttpLoggingInterceptor()
// interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
// val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
//
// val service = Retrofit.Builder().baseUrl("https://diplomatssummit.com").client(client).build().create(Service::class.java)
//
//
// val file = File(filePath)
//
// val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
// val body = MultipartBody.Part.createFormData("upload", file.getName(), reqFile)
// val name = RequestBody.create(MediaType.parse("text/plain"), "upload_test")
//
// val req = service.postImage(body, name)
//
// req.enqueue(object : Callback<ResponseBody> {
// override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
// // Do Something
// }
//
// override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
// t.printStackTrace()
// }
// })
// }
//
//
//
// }*