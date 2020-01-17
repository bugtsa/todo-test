package com.bugtsa.todo.data.entities

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TodoDAO {

    @Insert(onConflict = REPLACE)
    fun insertParameters(entity: List<TodoEntity>): Single<List<Long>>

    @Query("SELECT * FROM todo")
    fun observeTodoList(): Single<List<TodoEntity>>

    @Query("SELECT COUNT(*) FROM todo")
    fun getCountTodoList(): Single<Int>
}