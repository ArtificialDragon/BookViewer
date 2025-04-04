package com.dragonlordian.bookreader.di

import com.dragonlordian.bookreader.data.repositoryImpl.AllBookRepositoryImpl
import com.dragonlordian.bookreader.domain.repository.AllBookRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage{
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideRealTimeDatabase(): FirebaseDatabase{
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideAllBookRepository(firebaseDatabase: FirebaseDatabase): AllBookRepository{
        return AllBookRepositoryImpl(firebaseDatabase)
    }
}