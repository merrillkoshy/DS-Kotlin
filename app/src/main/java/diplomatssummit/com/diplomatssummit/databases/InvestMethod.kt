package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.InvestTable
import diplomatssummit.com.diplomatssummit.databases.core.InvestTable_Table
import diplomatssummit.com.diplomatssummit.databases.core.PartnersTable
import diplomatssummit.com.diplomatssummit.databases.core.PartnersTable_Table



class InvestMethod {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias() {

        val invList = SQLite.select().from(InvestTable::class.java).queryList()

        readMediaRowsBasedOnType(MEDIA_IMAGE)


    }


    fun readMediaRowsBasedOnType(mediaType: Long?): List<InvestTable> {

        val i = 0
        val operatorGroup = OperatorGroup.clause()
                .and(InvestTable_Table.MediaType.eq(mediaType))

        val invRowList = SQLite.select()
                .from(InvestTable::class.java)
                .where(operatorGroup)
                .queryList()

        return invRowList
    }

    fun itemsize(): Int {
        val operatorGroup = OperatorGroup.clause()
                .and(InvestTable_Table.MediaType.eq(1.toLong()))

        val invRowList = SQLite.select()
                .from(InvestTable::class.java)
                .where(operatorGroup)
                .queryList()
        return invRowList.size
    }


    companion object {

        val MEDIA_IMAGE = 1L

    }
}