package com.electropeyk.to_doapplication.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.electropeyk.to_doapplication.data.models.ToDoTask
import com.electropeyk.to_doapplication.data.repositories.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SharedViewModel @Inject constructor(private val repository: ToDoRepository) : ViewModel() {

    private val _allTasks = MutableStateFlow <List<ToDoTask>>(emptyList())
    val allTasks:StateFlow<List<ToDoTask>>  = _allTasks

    fun getAllTasks(){
         viewModelScope.launch {
             repository.getAllTasks.collect{
                _allTasks.value = it
             }
         }
     }
}