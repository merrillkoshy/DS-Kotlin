package diplomatssummit.com.diplomatssummit.databases.core

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class AchieveTable : BaseModel() {

    @PrimaryKey
    var Id: Long? = null

    @Column
    var Thumb: String? = null

    @Column
    var Shortdesc: String? = null

    @Column
    var Longdesc: String? = null


}