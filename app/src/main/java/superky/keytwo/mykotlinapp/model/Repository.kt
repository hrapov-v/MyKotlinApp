package superky.keytwo.mykotlinapp.model

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocal(): Weather

}