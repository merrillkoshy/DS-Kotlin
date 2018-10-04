package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.structure.BaseModel
import com.raizlabs.android.dbflow.annotation.Table
import android.R.attr.checked




@Table(name = "upcoming", database = MyDatabase::class, allFields = true)
class TleModel : BaseModel {
    companion object {
        const val NAME = "TleModel"
    }
    @PrimaryKey(autoincrement = true)
    @Column
    var uid: Long = 0L
    @Column
    var url: String = ""

    constructor() {

    }
    constructor(urls: Annotation) {
        urls.let {
            this.uid = uid
            this.url=url
        }
    }
//    fun toDomain(): Annotation =
//           urls(
////                    uid,
////                    url
//           )
////}
}
