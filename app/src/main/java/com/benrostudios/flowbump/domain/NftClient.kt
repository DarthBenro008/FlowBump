package com.benrostudios.flowbump.domain

import com.benrostudios.NFTModelQuery
import com.benrostudios.NFTModelsQuery
import com.benrostudios.WalletByIdQuery

interface NftClient {
    suspend fun getNfts(): NFTModelsQuery.NftModels
    suspend fun getNftById(id: String): NFTModelQuery.NftModel
    suspend fun getWalletById(id: String): WalletByIdQuery.WalletById
}