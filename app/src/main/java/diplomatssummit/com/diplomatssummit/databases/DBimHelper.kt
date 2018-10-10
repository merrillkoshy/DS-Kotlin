import android.content.Context
import android.util.Log
import diplomatssummit.com.diplomatssummit.databases.SampleMethods
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object DBimHelper {
  val dbimage:ArrayList<String>?=null

  fun populateDBimages() {

    val sampleObj = SampleMethods()
    val ar = sampleObj.readMediaRowsBasedOnType(1)
    val size = sampleObj.itemsize()
    var i = 0
    while (i < size) {
      var imagePath = ar[i].MediaUrl
      dbimage?.add(imagePath)
      Log.d("test", imagePath)
      i++
    }
  }
}
