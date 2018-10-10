package diplomatssummit.com.diplomatssummit.databases.core

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class PastEvents : BaseModel() {

    @Column
    var Id: Long? = null

    @Column
    var MediaType: Long? = null

    @PrimaryKey
    var MediaUrl: String? = null

    override fun toString(): String {
        return "Id - $Id,\nMediaType - $MediaType,\nMediaUrl - $MediaUrl"
    }
}
