package superky.keytwo.mykotlinapp.model

import superky.keytwo.mykotlinapp.view.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocal(): Weather

}