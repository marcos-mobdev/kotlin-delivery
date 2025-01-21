package br.com.appforge.kotlindelivery.di

import br.com.appforge.kotlindelivery.domain.usecase.AuthenticationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideAuthenticationUseCase():AuthenticationUseCase{
        return AuthenticationUseCase()
    }
}