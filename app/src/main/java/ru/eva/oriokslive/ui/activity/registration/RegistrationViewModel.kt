package ru.eva.oriokslive.ui.activity.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val startMainActivity = MutableLiveData<Unit>()
    val noToken = MutableLiveData<Unit>()

    fun checkAccessToken() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            domainRepository.getAccessToken()?.let {
                domainRepository.setStudent(remoteRepository.getStudent())
                startMainActivity.postValue(Unit)
            } ?: noToken.postValue(Unit)
        }
    }

    fun getAccessToken(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            remoteRepository.getAccessToken(login, password).let {
                domainRepository.setAccessToken(it.token)
                startMainActivity.postValue(Unit)
            }
        }
    }
    fun deleteToken() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.clearAll()
        }
    }
}
