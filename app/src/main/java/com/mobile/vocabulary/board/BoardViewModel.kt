package com.mobile.vocabulary.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.vocabulary.infra.network.VocabularyApi
import kotlinx.coroutines.launch

class BoardViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    init {
        getColumns()
    }

    private fun getColumns() {
//        _status.value = "Set the Mars API status response here!"
        viewModelScope.launch {
            val listResult = VocabularyApi.retrofitService.getColumns()
//            _status.value = listResult
        }
    }
}