package com.example.anew.di

import android.app.Application
import androidx.room.Room
import com.example.anew.data.local.NewsDao
import com.example.anew.data.local.NewsDatabase
import com.example.anew.data.local.NewsTypeConverter
import com.example.anew.data.manger.LocalUserMangerImpl
import com.example.anew.util.Constants.BASE_URL
import com.example.anew.util.Constants.NEWS_DATABASE_NAME
import com.example.anew.data.remote.NewsApi
import com.example.anew.data.repository.NewsRepositoryImpl
import com.example.anew.domain.manger.LocalUserManger
import com.example.anew.domain.repository.NewsRepository
import com.example.anew.domain.usecases.app_entry.AppEntryUseCases
import com.example.anew.domain.usecases.app_entry.ReadAppEntry
import com.example.anew.domain.usecases.app_entry.SaveAppEntry
import com.example.anew.domain.usecases.news.DeleteArticle
import com.example.anew.domain.usecases.news.GetNews
import com.example.anew.domain.usecases.news.NewsUseCases
import com.example.anew.domain.usecases.news.SearchNews
import com.example.anew.domain.usecases.news.SelectArticle
import com.example.anew.domain.usecases.news.SelectArticles
import com.example.anew.domain.usecases.news.UpsertArticle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger (
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases (
        localUserManger: LocalUserManger
    ) = AppEntryUseCases (
        readAppEntry = ReadAppEntry(localUserManger = localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger = localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository (
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl (
        newsApi = newsApi,
        newsDao = newsDao
    )

    @Provides
    @Singleton
    fun provideNewsUseCases (
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases (
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase (
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder (
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao (
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}