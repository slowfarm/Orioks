package ru.eva.oriokslive.ui.activity.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val startMainActivity = MutableLiveData<Unit>()
    val errorMessage = MutableLiveData<Unit>()

    fun checkAccessToken() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getAccessToken()?.let { startMainActivity.postValue(Unit) }
        }
    }

    fun getAccessToken(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getAccessToken(login, password)?.let {
                domainRepository.setAccessToken(it.token)
                startMainActivity.postValue(Unit)
            } ?: errorMessage.postValue(Unit)
        }
    }
}
