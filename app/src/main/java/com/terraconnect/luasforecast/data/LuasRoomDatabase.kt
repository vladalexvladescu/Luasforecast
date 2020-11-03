package com.terraconnect.luasforecast.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.terraconnect.luasforecast.dao.StatusDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [RoomSavedDirection::class], version = 1)
abstract class LuasRoomDatabase : RoomDatabase() {

    abstract fun statusDao(): StatusDAO

    companion object {
        @Volatile
        private var INSTANCE: LuasRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): LuasRoomDatabase {
            // creating the database if is null
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LuasRoomDatabase::class.java,
                    "luas_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.statusDao())
                    }
                }
            }
        }
        //mock for testing
        fun populateDatabase(statusDAO: StatusDAO) {
            statusDAO.deleteAll()
            var index = "6"
            var entityMock :RoomSavedDirection
            val names = listOf("bau", "REFREW", "Green ", " Line services operating normally", "Green Line  operating normally", "Green Line services operating ", "Green Line services  normally", "Green Line services ", "Green Line y", " Line  operating ", "Green Line services = ", "Green Line services d normally", "Green Line k operating normally", "Green Line df operating normally", "Green Line f operating normally", "Green Line services lks normally", "Green Line services lkd normally", "Green Line  operating d", "Green Line services operating normally", "Green Line ssl operating normally", "Green k operating normally")
            for (name in names) {
                Log.d("fjhlerjfler","intra ")
                entityMock = RoomSavedDirection(name,index)
                statusDAO.insert(entityMock)
            }


        }
    }

}
