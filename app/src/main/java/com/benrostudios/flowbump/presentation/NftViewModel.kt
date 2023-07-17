package com.benrostudios.flowbump.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.benrostudios.NFTModelQuery
import com.benrostudios.WalletByIdQuery
import com.benrostudios.flowbump.domain.GetNftsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NftViewModel @Inject constructor (
    private val nftsUseCase: GetNftsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NftState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
        }
    }


    suspend fun getWallet(id: String){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    isLoading = false,
                    wallet = nftsUseCase.executeGetWalletById(id)
                )
            }
        }
    }

    data class NftState(
        val isLoading: Boolean = false,
        val specificNft: NFTModelQuery.NftModel? = null,
        val ownNft: NFTModelQuery.NftModel? = null,
        val wallet: WalletByIdQuery.WalletById? = null
    )
}