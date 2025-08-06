package com.example.dummychatapp1.model.module

import com.example.dummychatapp1.model.service.AccountService
import com.example.dummychatapp1.model.service.impl.AccountServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
}