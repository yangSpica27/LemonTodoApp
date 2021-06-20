package spica.lemon.plan.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 持久层相关的依赖注入
 */
@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule(

)