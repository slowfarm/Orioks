package ru.eva.oriokslive.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.ui.base.BaseViewModel
import ru.eva.oriokslive.ui.entity.NewsItem
import ru.eva.oriokslive.utils.mapNews
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) : BaseViewModel() {

    val news = MutableLiveData<List<NewsItem>>()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            news.postValue(mapNews(remoteRepository.getNews(), domainRepository.getCookie()))
        }
    }
}
