package com.example.application.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.application.entities.Team
import com.example.application.entities.User

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams ORDER BY id")
    fun fetchAllTeams(): MutableList<Team?>?

    @Query("SELECT * FROM users ORDER BY id")
    fun fetchAllUsers(): MutableList<User?>?

    @Query("SELECT * FROM teams WHERE id = :id")
    fun fetchTeamById(id: Int): Team?

    @Query("SELECT * FROM users WHERE id = :id")
    fun fetchUserById(id: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateTeam(team: Team)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(team: Team)
}