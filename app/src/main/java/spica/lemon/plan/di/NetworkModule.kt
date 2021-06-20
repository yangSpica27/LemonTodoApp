package spica.lemon.plan.di

import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import spica.lemon.plan.network.LoggingInterceptor
import spica.lemon.plan.network.NetworkService
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * 网络相关的依赖管理
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .retryOnConnectionFailure(true) // 自动重连
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .addInterceptor(LoggingInterceptor())
    .writeTimeout(15, TimeUnit.MILLISECONDS)
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("http://192.168.43.81//back/")
      .addConverterFactory(MoshiConverterFactory.create())
      .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideNetworkService(retrofit: Retrofit): NetworkService {
    return retrofit.create(NetworkService::class.java)
  }
}