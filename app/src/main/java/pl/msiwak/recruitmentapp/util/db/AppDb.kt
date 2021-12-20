package pl.msiwak.recruitmentapp.util.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.msiwak.recruitmentapp.data.ListItem

@Database(
    version = 1,
    entities = [ListItem::class]
)

abstract class AppDB : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {

        @Volatile
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDB {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDB::class.java,
                "RecruitmentApp.db"
            )
                .build()
        }
    }


}