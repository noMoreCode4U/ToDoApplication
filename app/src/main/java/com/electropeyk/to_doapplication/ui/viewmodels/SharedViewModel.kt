package com.electropeyk.to_doapplication.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.data.repositories.ToDoRepository
import com.electropeyk.to_doapplication.util.RequestState
import com.electropeyk.to_doapplication.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: ToDoRepository
):ViewModel() {

    private val _allTasks = MutableStateFlow <RequestState<List<ToDoTask>>>(RequestState.Idle)
    val allTasks:StateFlow<RequestState<List<ToDoTask>>>  = _allTasks

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    val searchTextState: MutableState<String> =
        mutableStateOf("")


    fun getAllTasks(){
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getAllTasks.collect{
                    _allTasks.value = RequestState.Success(it)
                }
            }
        }catch (e: Exception){
            _allTasks.value = RequestState.Error(e)
        }
     }
}