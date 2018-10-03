package diplomatssummit.com.diplomatssummit.databases

import com.raizlabs.android.dbflow.annotation.Database


@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
object MyDatabase {
    const val NAME = "tl_events"
    const val VERSION = 1
}