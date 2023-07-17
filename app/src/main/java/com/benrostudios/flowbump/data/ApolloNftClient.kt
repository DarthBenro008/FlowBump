package com.benrostudios.flowbump.data

import com.apollographql.apollo3.ApolloClient
import com.benrostudios.NFTModelQuery
import com.benrostudios.NFTModelsQuery
import com.benrostudios.WalletByIdQuery
import com.benrostudios.flowbump.domain.NftClient
import com.benrostudios.type.NFTModelBlockchainState
import com.benrostudios.type.WalletState

class ApolloNftClient(
    private val apolloClient: ApolloClient
) : NftClient {

    override suspend fun getNfts(): NFTModelsQuery.NftModels {
        return apolloClient.query(NFTModelsQuery()).execute().data?.nftModels
            ?: NFTModelsQuery.NftModels(items = emptyList())
    }

    override suspend fun getNftById(id: String): NFTModelQuery.NftModel {
        return apolloClient.query(NFTModelQuery(id)).execute().data?.nftModel
            ?: NFTModelQuery.NftModel(
                id = "",
                state = NFTModelBlockchainState.ERROR,
                attributes = null,
                metadata = null,
                content = null
            )
    }

    override suspend fun getWalletById(id: String): WalletByIdQuery.WalletById {
        return apolloClient.query(WalletByIdQuery(wallet_id = id)).execute().data?.walletById
            ?: WalletByIdQuery.WalletById(
                id = "",
                address = null,
                nftCount = null,
                nfts = emptyList(),
                state = WalletState.UNKNOWN__
            )
    }


}