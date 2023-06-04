package ru.eva.oriokslive.ui.fragment.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.eva.oriokslive.ui.entity.NewsItem
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.utils.mapNews
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val news = MutableLiveData<List<NewsItem>>()
    val onError = MutableLiveData<Unit>()

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteRepository.getNews()?.let { news.postValue(mapNews(it)) }
                ?: onError.postValue(Unit)
        }
    }
}
