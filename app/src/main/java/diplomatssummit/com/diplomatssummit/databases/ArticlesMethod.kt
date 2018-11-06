package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.*

class ArticlesMethod {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias(): MutableList<ArticlesTable> {

        val articleslist = SQLite.select().from(ArticlesTable::class.java).queryList()

        return articleslist


    }

    fun itemsize(): Int {

        val articleslist = SQLite.select().from(ArticlesTable::class.java).queryList()
        return articleslist.size
    }



}