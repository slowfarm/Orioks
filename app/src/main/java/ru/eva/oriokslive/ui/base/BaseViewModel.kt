package ru.eva.oriokslive.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    val errorMessage = MutableLiveData(Unit)
}