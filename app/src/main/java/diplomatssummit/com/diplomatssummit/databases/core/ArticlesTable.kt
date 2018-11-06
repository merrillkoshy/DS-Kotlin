package diplomatssummit.com.diplomatssummit.databases.core

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(database = AppDatabase::class)
class ArticlesTable : BaseModel() {

    @PrimaryKey
    var Id: Long? = null

    @Column
    var ArticleImage: String? = null

    @Column
    var ArticleHeading: String? = null

    @Column
    var ArticlePreview: String? = null

    @Column
    var ArticleDetailed: String? = null

}