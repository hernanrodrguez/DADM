package com.example.application.database

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.application.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartingUsers (private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("StartingUsers", "Pre-populating database...")
            fillWithStartingUsers(context)
        }
    }

    private fun fillWithStartingUsers(context: Context) {
        val users = listOf(
            User(1, "hernan", "hernan@example.com", "River Plate", "Buenos Aires", "https://picsum.photos/id/22/300/300", "1234"),
            User(2, "alejandro", "ale@example.com", "Barcelona", "Buenos Aires", "https://picsum.photos/id/27/300/300", "5678"),
            User(3, "matias", "matias@example.com", "Manchester City", "Buenos Aires", "https://picsum.photos/id/91/300/300", "abcd"),
            User(4, "juan",  "juan@example.com", "San Lorenzo", "Tucuman", "https://picsum.photos/id/103/300/300", "efgh"),
            User(5, "lucas", "lucas@example.com", "Ferro", "Salta", "https://picsum.photos/id/129/300/300", "ijkl"),
            User(6, "maria", "maria@example.com", "PSG", "Bariloche", "https://picsum.photos/id/64/300/300", "mnop"),
            User(7, "ana", "ana@example.com", "Atlas", "New York", "https://picsum.photos/id/65/300/300", "qrst"),
            User(8, "carlos", "carlos@example.com", "Sacachispas", "Doha", "https://picsum.photos/id/177/300/300" ,"uvwx"),
            User(9, "laura", "laura@example.com", "Gryffindor", "Hogsmeade", "https://picsum.photos/id/203/300/300", "yz12"),
            User(10, "pedro", "pedro@example.com", "Chelsea", "Londres", "https://picsum.photos/id/209/300/300", "3456")
        )
        val dao = AppDatabase.getInstance(context)?.teamDao()

        users.forEach {
            dao?.insertUser(it)
        }
    }
}