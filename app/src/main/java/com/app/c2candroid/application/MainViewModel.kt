package com.app.c2candroid.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.c2candroid.model.Exhibit
import com.app.c2candroid.restExhibitLoader.repository.ExhibitRepository
import com.app.c2candroid.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val mainRepository: ExhibitRepository,
): ViewModel(){



    private val _dataState: MutableLiveData<DataState<List<Exhibit>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<Exhibit>>>
    get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent){
        viewModelScope.launch {
            when(mainStateEvent){
                is MainStateEvent.GetWeatherEvents -> {
                    mainRepository.getExhibit()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                is MainStateEvent.None -> {
                    //do nothing
                }
            }
        }
    }

}

sealed class MainStateEvent(){
    object GetWeatherEvents: MainStateEvent()

    object None: MainStateEvent()
}
