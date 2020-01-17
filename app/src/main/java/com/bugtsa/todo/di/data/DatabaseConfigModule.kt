package com.bugtsa.todo.di.data

import android.content.Context
import androidx.room.Room
import com.bugtsa.todo.data.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseConfigModule {

    private const val DATABASE_NAME = "bugtsa_todos"

    val module by lazy {
        module {
            single { getDatabase(androidContext()) }
            single { get<Database>().getTodoDAO() }
        }
    }

    private fun getDatabase(
        context: Context
    ): Database {
        return Room.databaseBuilder(context, Database::class.java, DATABASE_NAME)
            .build()
    }
}