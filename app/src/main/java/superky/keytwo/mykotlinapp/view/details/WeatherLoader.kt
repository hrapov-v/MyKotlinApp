package superky.keytwo.mykotlinapp.view.details

import com.google.gson.Gson
import superky.keytwo.mykotlinapp.model.WeatherDTO
import superky.keytwo.mykotlinapp.model.YANDEX_API_KEY_NAME
import superky.keytwo.mykotlinapp.model.YANDEX_API_KEY_VALUE
import superky.keytwo.mykotlinapp.model.YANDEX_API_URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {
    fun loadWeather() {
        Thread {
            try {
                val url = URL("${YANDEX_API_URL}?lat= ${lat}&lon= ${lon}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(YANDEX_API_KEY_NAME, YANDEX_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                listener.onLoaded(weatherDTO)
            } catch (e: Exception) {
                listener.onFailed(e)
            }
        }
    }
}

interface WeatherLoaderListener {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFailed(throwable: Throwable)
}