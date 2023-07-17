package com.benrostudios.flowbump.di

import com.apollographql.apollo3.ApolloClient
import com.benrostudios.flowbump.BuildConfig
import com.benrostudios.flowbump.data.ApolloNftClient
import com.benrostudios.flowbump.domain.GetNftsUseCase
import com.benrostudios.flowbump.domain.NftClient
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
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://graphql.api.staging.niftory.com/")
            .addHttpHeader("X-Niftory-API-Key", BuildConfig.API_KEY?: "")
            .addHttpHeader(
                "X-Niftory-Client-Secret",
                BuildConfig.API_SECRET?: ""
            ).build()
    }

    @Provides
    @Singleton
    fun provideNftClient(apolloClient: ApolloClient): NftClient {
        return ApolloNftClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideNftUseCase(nftClient: NftClient): GetNftsUseCase {
        return GetNftsUseCase(nftClient)
    }

}