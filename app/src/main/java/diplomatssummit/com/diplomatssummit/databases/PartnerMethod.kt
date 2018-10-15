package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.PartnersTable
import diplomatssummit.com.diplomatssummit.databases.core.PartnersTable_Table

class PartnerMethod {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias() {

        val galleryList = SQLite.select().from(PartnersTable::class.java).queryList()

        readMediaRowsBasedOnType(MEDIA_IMAGE)


    }


    fun readMediaRowsBasedOnType(mediaType: Long?): List<PartnersTable> {

        val i = 0
        val operatorGroup = OperatorGroup.clause()
                .and(PartnersTable_Table.MediaType.eq(mediaType))

        val galleryRowList = SQLite.select()
                .from(PartnersTable::class.java)
                .where(operatorGroup)
                .queryList()

        return galleryRowList
    }

    fun itemsize(): Int {
        val operatorGroup = OperatorGroup.clause()
                .and(PartnersTable_Table.MediaType.eq(1.toLong()))

        val galleryRowList = SQLite.select()
                .from(PartnersTable::class.java)
                .where(operatorGroup)
                .queryList()
        return galleryRowList.size
    }


    companion object {

        val MEDIA_IMAGE = 1L

    }
}