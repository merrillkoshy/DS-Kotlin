package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.sql.language.OperatorGroup
import com.raizlabs.android.dbflow.sql.language.SQLite
import diplomatssummit.com.diplomatssummit.databases.core.AchieveTable
import diplomatssummit.com.diplomatssummit.databases.core.AchieveTable_Table
import diplomatssummit.com.diplomatssummit.databases.core.CtyInvTable
import diplomatssummit.com.diplomatssummit.databases.core.CtyInvTable_Table

class AchievementsMethod {

    private val TAG = this.javaClass.simpleName

    fun readAllMedias(): MutableList<AchieveTable> {

        val achievementslist = SQLite.select().from(AchieveTable::class.java).queryList()

        return achievementslist


    }

    fun itemsize(): Int {

        val achievementslist = SQLite.select().from(AchieveTable::class.java).queryList()
        return achievementslist.size
    }



}