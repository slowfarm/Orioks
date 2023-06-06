package ru.eva.oriokslive.ui.activity.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
) : ViewModel() {
    val cookie = MutableLiveData<String>()

    fun getCookie() {
        viewModelScope.launch(Dispatchers.IO) {
            domainRepository.getCookie()?.let { cookie.postValue(it) }
        }
    }
}