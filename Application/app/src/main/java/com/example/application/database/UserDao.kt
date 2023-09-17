package com.example.application.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.application.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users ORDER BY id")
    fun fetchAllUsers(): MutableList<User?>?

    @Query("SELECT * FROM users WHERE id = :id")
    fun fetchUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)
}