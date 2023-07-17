package com.benrostudios.flowbump.domain

import com.benrostudios.NFTModelQuery
import com.benrostudios.NFTModelsQuery
import com.benrostudios.WalletByIdQuery

class GetNftsUseCase(
    private val nftClient: NftClient
) {

    suspend fun executeGetAllNfts(): NFTModelsQuery.NftModels {
        return nftClient.getNfts()
    }

    suspend fun executeGetNftById(id: String): NFTModelQuery.NftModel {
        return nftClient.getNftById(id)
    }

    suspend fun executeGetWalletById(id: String): WalletByIdQuery.WalletById {
        return nftClient.getWalletById(id)
    }

}