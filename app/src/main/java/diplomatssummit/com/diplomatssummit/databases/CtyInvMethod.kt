package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.CtyInvTable
import diplomatssummit.com.diplomatssummit.databases.core.CtyInvTable_Table

class CtyInvMethod {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias() {

        val galleryList = SQLite.select().from(CtyInvTable::class.java).queryList()

        readMediaRowsBasedOnType(MEDIA_IMAGE)


    }


    fun readMediaRowsBasedOnType(mediaType: Long?): List<CtyInvTable> {

        val i = 0
        val operatorGroup = OperatorGroup.clause()
                .and(CtyInvTable_Table.MediaType.eq(mediaType))

        val galleryRowList = SQLite.select()
                .from(CtyInvTable::class.java)
                .where(operatorGroup)
                .queryList()

        return galleryRowList
    }

    fun readContentFromTitle(Title:String): List<CtyInvTable>{
        val operatorGroup = OperatorGroup.clause()
                .and(CtyInvTable_Table.Country.eq(Title))

        val contentList=SQLite.select(CtyInvTable_Table.MediaUrl).
                from(CtyInvTable::class.java).
                where(operatorGroup)
                .queryList()

        return contentList

    }

    fun itemsize(): Int {
        val operatorGroup = OperatorGroup.clause()
                .and(CtyInvTable_Table.MediaType.eq(1.toLong()))

        val galleryRowList = SQLite.select()
                .from(CtyInvTable::class.java)
                .where(operatorGroup)
                .queryList()
        return galleryRowList.size
    }


    companion object {

        val MEDIA_IMAGE = 1L

    }
}