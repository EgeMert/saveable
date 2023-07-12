package com.pixelcreative.saveable.di

import android.content.Context
import androidx.room.Room
import com.pixelcreative.saveable.core.Constants.Companion.EXPENSE_TABLE
import com.pixelcreative.saveable.data.dao.ExpenseDao
import com.pixelcreative.saveable.data.dao.ExpenseDetailDao
import com.pixelcreative.saveable.data.network.ExpenseDb
import com.pixelcreative.saveable.data.repository.ExpenseDetailRepositoryImpl
import com.pixelcreative.saveable.data.repository.ExpenseRepositoryImpl
import com.pixelcreative.saveable.domain.repository.ExpenseDetailRepository
import com.pixelcreative.saveable.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideExpenseDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        ExpenseDb::class.java,
        EXPENSE_TABLE
    ).build()

    @Provides
    fun provideExpenseDao(
        expenseDb: ExpenseDb
    ) = expenseDb.expenseDao

    @Provides
    fun provideExpenseRepository(
        expenseDao: ExpenseDao
    ): ExpenseRepository = ExpenseRepositoryImpl(
        expenseDao = expenseDao
    )

    @Provides
    fun provideExpenseDetailRepository(
        expenseDetailDao: ExpenseDetailDao
    ): ExpenseDetailRepository = ExpenseDetailRepositoryImpl(
        expenseDetailDao = expenseDetailDao
    )
}