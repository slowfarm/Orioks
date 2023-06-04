package ru.eva.oriokslive.ui.fragment.security

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.entity.SecurityItem
import ru.eva.oriokslive.utils.mapTokens
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val tokens = MutableLiveData<List<SecurityItem>>()
    val onError = MutableLiveData<Unit>()
    private var items = mutableListOf<SecurityItem>()

    fun getActiveTokens() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getAllActiveTokens()?.let {
                items = mapTokens(it)
                tokens.postValue(items)
            } ?: onError.postValue(Unit)
        }
    }

    fun deleteToken(token: String, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.deleteAccessToken(token)?.let {
                items.removeAt(position)
                tokens.postValue(items)
            } ?: onError.postValue(Unit)
        }
    }
}