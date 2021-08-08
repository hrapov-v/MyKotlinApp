package superky.keytwo.mykotlinapp.view.details

import superky.keytwo.mykotlinapp.model.WeatherDTO
import superky.keytwo.mykotlinapp.model.YANDEX_API_URL
import java.net.URL

class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val long: Double
) {
    fun loadWeather() {
        val url = URL(YANDEX_API_URL)
    }
}

interface WeatherLoaderListener {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFailed(throwable: Throwable)
}