package spica.lemon.plan.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import spica.lemon.plan.persistence.AppDatabase
import javax.inject.Singleton

/**
 * 持久层相关的依赖注入
 */
@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "spica_todo.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}