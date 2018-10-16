package diplomatssummit.com.diplomatssummit.databases.core

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class GalleryTable : BaseModel() {

    @PrimaryKey
    var Id: Long? = null

    @Column
    var MediaType: Long? = null

    @Column
    var MediaUrl: String? = null

    @Column
    var GalleryThumb: String? = null

    @Column
    var Title: String? = null

    override fun toString(): String {
        return "$MediaUrl"
    }
}