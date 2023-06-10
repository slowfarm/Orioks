package ru.eva.oriokslive.ui.fragment.security

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.SecurityItem
import ru.eva.oriokslive.utils.mapper.mapSecurity
import javax.inject.Inject

@HiltViewModel
class SecurityViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val tokens = MutableLiveData<List<SecurityItem>>()
    val finishActivity = MutableLiveData<Unit>()
    private var items = mutableListOf<SecurityItem>()

    fun getActiveTokens() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getAllActiveTokens().let {
                items = mapSecurity(it)
                tokens.postValue(items)
            }
        }
    }

    fun deleteToken(token: String, position: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.deleteAccessToken(token)
            items.removeAt(position)
            tokens.postValue(items)
            if (token == domainRepository.getAccessToken()) {
                domainRepository.clearAll()
                finishActivity.postValue(Unit)
            }
        }
    }
}