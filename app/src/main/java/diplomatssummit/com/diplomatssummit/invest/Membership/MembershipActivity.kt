package diplomatssummit.com.diplomatssummit.invest.Membership

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View.GONE
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.squareup.picasso.Picasso
import diplomatssummit.com.diplomatssummit.R
import kotlinx.android.synthetic.main.activity_membership.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.Volley
import diplomatssummit.com.diplomatssummit.Networking.ServerResponse
import diplomatssummit.com.diplomatssummit.Networking.UploadImage
import diplomatssummit.com.diplomatssummit.Networking.VolleyMultipartRequest
import diplomatssummit.com.diplomatssummit.app_ui.CustomDropDownAdapter

import okhttp3.*
import okhttp3.RequestBody
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject


class MembershipActivity : AppCompatActivity() {

    private val GALLERY = 1
    private val CAMERA = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership)
        val photoupload:TextView=findViewById(R.id.UploadPhoto)
        photoupload.setOnClickListener {
            showPictureDialog()
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


private fun showPictureDialog() {
    val pictureDialog = AlertDialog.Builder(this)
    pictureDialog.setTitle("Upload Your Photo")
    val pictureDialogItems = arrayOf("Select photo from gallery")
    pictureDialog.setItems(pictureDialogItems
    ) { dialog, which ->
        when (which) {
            0 -> choosePhotoFromGallary()
        }
    }
    pictureDialog.show()
}

fun choosePhotoFromGallary() {

    val intent=Intent(this,UploadImage::class.java)
    startActivity(intent)
/*    val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

    startActivityForResult(galleryIntent, GALLERY)*/
}


public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

    super.onActivityResult(requestCode, resultCode, data)
    /* if (resultCode == this.RESULT_CANCELED)
     {
     return
     }*/
    if (requestCode == GALLERY)
    {
        if (data != null)
        {
            val contentURI = data.data
            try
            {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                val path = saveImage(bitmap)
                Log.d("ShowPath",path)
                Toast.makeText(this@MembershipActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                Picasso.get().load(contentURI).fit().placeholder(R.drawable.progress_animation).into(iv)


            }
            catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this@MembershipActivity, "Failed!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}




    fun saveImage(myBitmap: Bitmap):String {
    val bytes = ByteArrayOutputStream()
    myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
    val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY)
    // have the object build the directory structure, if needed.
    Log.d("fee",wallpaperDirectory.toString())
    if (!wallpaperDirectory.exists())
    {

        wallpaperDirectory.mkdirs()
    }

    try
    {
        Log.d("heel",wallpaperDirectory.toString())
        val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
        f.createNewFile()
        val fo = FileOutputStream(f)
        fo.write(bytes.toByteArray())
        MediaScannerConnection.scanFile(this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
        fo.close()
        Log.d("TAG", "File Saved::--->" + f.getAbsolutePath())

        return f.getAbsolutePath()
    }
    catch (e1: IOException) {
        e1.printStackTrace()
    }

    return ""
}

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


companion object {
    private val IMAGE_DIRECTORY = "/gallerytest"
}
}
