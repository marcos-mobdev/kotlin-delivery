package br.com.appforge.kotlindelivery.di

import br.com.appforge.kotlindelivery.data.remote.firebase.repository.AuthenticationRepositoryImpl
import br.com.appforge.kotlindelivery.data.remote.firebase.repository.IAuthenticationRepository
import br.com.appforge.kotlindelivery.domain.usecase.AuthenticationUseCase
import com.google.firebase.auth.FirebaseAuth
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

    @Provides
    fun provideAuthenticationRepository(firebaseAuth: FirebaseAuth):IAuthenticationRepository{
        return AuthenticationRepositoryImpl(firebaseAuth)
    }

    @Provides
    fun provideFirebaseAuth():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }


}