package diplomatssummit.com.diplomatssummit.databases

import android.util.Log
import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.MediaTable
import diplomatssummit.com.diplomatssummit.databases.core.MediaTable_Table
import diplomatssummit.com.diplomatssummit.databases.core.PastEvents
import diplomatssummit.com.diplomatssummit.databases.core.PastEvents_Table

class PeMethods {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias() {

        val pastEventsList = SQLite.select().from(PastEvents::class.java).queryList()

        readMediaRowsBasedOnType(MEDIA_IMAGE)


    }


    fun readMediaRowsBasedOnType(mediaType: Long?): List<PastEvents> {

        val i = 0
        val operatorGroup = OperatorGroup.clause()
                .and(PastEvents_Table.MediaType.eq(mediaType))

        val pastRowList = SQLite.select()
                .from(PastEvents::class.java)
                .where(operatorGroup)
                .queryList()

        return pastRowList
    }

    fun itemsize(): Int {
        val operatorGroup = OperatorGroup.clause()
                .and(PastEvents_Table.MediaType.eq(1.toLong()))

        val pastRowList = SQLite.select()
                .from(PastEvents::class.java)
                .where(operatorGroup)
                .queryList()
        return pastRowList.size
    }


    companion object {

        val MEDIA_IMAGE = 1L

    }
}
