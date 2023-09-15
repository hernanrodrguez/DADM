package com.example.application.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.application.entities.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams ORDER BY id")
    fun fetchAllTeams(): MutableList<Team?>?

    @Query("SELECT * FROM teams WHERE id = :id")
    fun fetchTeamById(id: Int): Team?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeam(team: Team)

    @Update
    fun updateTeam(team: Team)

    @Delete
    fun delete(team: Team)
}