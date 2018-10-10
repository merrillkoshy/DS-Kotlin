package diplomatssummit.com.diplomatssummit.databases.core

import com.raizlabs.android.dbflow.annotation.Database


@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "local_database"
    const val VERSION: Int = 1
}
