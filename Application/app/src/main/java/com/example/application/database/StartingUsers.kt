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
            User(1, "hernan", "1234"),
            User(2, "alejandro", "5678"),
            User(3, "matias", "abcd"),
            User(4, "juan", "efgh"),
            User(5, "lucas", "ijkl"),
            User(6, "maria", "mnop"),
            User(7, "ana", "qrst"),
            User(8, "carlos", "uvwx"),
            User(9, "laura", "yz12"),
            User(10, "pedro", "3456")
        )
        val dao = AppDatabase.getInstance(context)?.teamDao()

        users.forEach {
            dao?.insertUser(it)
        }
    }
}