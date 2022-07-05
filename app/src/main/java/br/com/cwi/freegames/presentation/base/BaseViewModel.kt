package br.com.cwi.freegames.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    fun launch(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                block()
                //TODO: LOADING LIVE DATA
            } catch (ex: Exception) {
                //TODO: ERROR LIVE DATA
            }
            //TODO: LOADING LIVE DATA
        }
    }
}