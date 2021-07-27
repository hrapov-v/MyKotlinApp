package superky.keytwo.mykotlinapp.viewmodel

import superky.keytwo.mykotlinapp.model.Weather

sealed class AppState {
    data class Succes(val dataWeather: Weather): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()

}