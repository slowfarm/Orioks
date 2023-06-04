package ru.eva.oriokslive.ui.fragment.security

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.network.entity.orioks.Security
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.utils.mapTokens
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val tokens = MutableLiveData<List<Security>>()
    private var items = mutableListOf<Security>()

    fun getActiveTokens() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getAllActiveTokens()?.let {
                items = mapTokens(it)
                tokens.postValue(items)
            }
        }
    }

    fun deleteToken(token: Security, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.deleteAccessToken(token.token)?.let {
                items.removeAt(position)
                tokens.postValue(items)
            } ?: errorMessage.postValue(Unit)
        }
    }
}