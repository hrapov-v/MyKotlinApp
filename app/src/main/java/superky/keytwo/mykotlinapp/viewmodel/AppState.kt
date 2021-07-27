package superky.keytwo.mykotlinapp.viewmodel

sealed class AppState {
    data class Succes(val dataWeather: Any): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading

}