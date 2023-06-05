package ru.eva.oriokslive.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {
    val onError = MutableLiveData<Throwable>()

    val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
        run {
            Timber.e(t)
            onError.postValue(t)
        }
    }
}