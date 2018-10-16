package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.GalleryTable
import diplomatssummit.com.diplomatssummit.databases.core.GalleryTable_Table

class GtableMethods {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias() {

        val galleryList = SQLite.select().from(GalleryTable::class.java).queryList()

        readMediaRowsBasedOnType(MEDIA_IMAGE)


    }


    fun readMediaRowsBasedOnType(mediaType: Long?): List<GalleryTable> {

        val i = 0
        val operatorGroup = OperatorGroup.clause()
                .and(GalleryTable_Table.MediaType.eq(mediaType))

        val galleryRowList = SQLite.select()
                .from(GalleryTable::class.java)
                .where(operatorGroup)
                .queryList()

        return galleryRowList
    }

    fun readContentFromTitle(Title:String): List<GalleryTable>{
        val operatorGroup = OperatorGroup.clause()
                .and(GalleryTable_Table.Title.eq(Title))

        val contentList=SQLite.select(GalleryTable_Table.MediaUrl).
                from(GalleryTable::class.java).
                where(operatorGroup)
                .queryList()

        return contentList

    }

    fun itemsize(): Int {
        val operatorGroup = OperatorGroup.clause()
                .and(GalleryTable_Table.MediaType.eq(1.toLong()))

        val galleryRowList = SQLite.select()
                .from(GalleryTable::class.java)
                .where(operatorGroup)
                .queryList()
        return galleryRowList.size
    }


    companion object {

        val MEDIA_IMAGE = 1L

    }
}
