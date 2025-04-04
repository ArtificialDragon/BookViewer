package com.dragonlordian.bookreader.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dragonlordian.bookreader.data.model.BookCategory
import com.dragonlordian.bookreader.data.model.BookModel
import com.dragonlordian.bookreader.data.model.ResultState
import com.dragonlordian.bookreader.domain.repository.AllBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    val repository: AllBookRepository
) : ViewModel() {

    private val _state: MutableState<ItemState> = mutableStateOf(ItemState())
    val state: State<ItemState> = _state

    fun getAllBooks() {
        viewModelScope.launch {
            repository.getAllBooks().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            repository.getAllCategory().collect {
                when (it) {
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }

                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }

                    is ResultState.Success -> {
                        _state.value = ItemState(category = it.data)
                    }
                }
            }
        }
    }

    fun getAllBooksByCategory(category: String) {
        viewModelScope.launch {
            repository.getAllBooksByCategory(category).collect {
                when(it){
                    is ResultState.Loading -> {
                        _state.value = ItemState(isLoading = true)
                    }
                    is ResultState.Error -> {
                        _state.value = ItemState(error = it.exception.localizedMessage)
                    }
                    is ResultState.Success -> {
                        _state.value = ItemState(items = it.data)
                    }
                }
            }
        }
    }
}


data class ItemState(
    val isLoading: Boolean = false,
    val items: List<BookModel> = emptyList(),
    val error: String = "",
    val category: List<BookCategory> = emptyList()
)