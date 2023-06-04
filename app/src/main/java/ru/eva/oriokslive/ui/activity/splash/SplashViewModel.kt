package ru.eva.oriokslive.ui.activity.splash

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
class SplashViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val startActivity = MutableLiveData<Unit>()
    val onError = MutableLiveData<Unit>()

    fun checkAccessToken() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getAccessToken()?.let {
                remoteRepository.getStudent()?.let {
                    domainRepository.setStudent(it)
                    startActivity.postValue(Unit)
                } ?: deleteToken()
            } ?: deleteToken()
        }
    }

    private suspend fun deleteToken() {
        domainRepository.clearAll()
        onError.postValue(Unit)
    }
}