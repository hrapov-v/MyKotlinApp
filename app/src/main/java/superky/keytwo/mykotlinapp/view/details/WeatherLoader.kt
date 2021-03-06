package superky.keytwo.mykotlinapp.view.details

import android.os.Handler
import com.google.gson.Gson
import superky.keytwo.mykotlinapp.model.WeatherDTO
import superky.keytwo.mykotlinapp.model.YANDEX_API_KEY_NAME
import superky.keytwo.mykotlinapp.model.YANDEX_API_KEY_VALUE
import superky.keytwo.mykotlinapp.model.YANDEX_API_URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(
    private val listener: WeatherLoaderListener,
    private val lat: Double,
    private val lon: Double
) {
    fun loadWeather() {
        val handler = Handler()
        Thread {

            try {
                val url = URL("${YANDEX_API_URL}?lat=${lat}&lon=${lon}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(YANDEX_API_KEY_NAME, YANDEX_API_KEY_VALUE)
                //httpsURLConnection.addRequestProperty(YANDEX_API_KEY_NAME, BuildConfig.YANDEX_API_KEY_NAME)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                handler.post(Runnable { listener.onLoaded(weatherDTO) })
            } catch (e: Exception) {
                handler.post(Runnable { listener.onFailed(e) })
            }

        }.start()


    }
}

interface WeatherLoaderListener {
    fun onLoaded(weatherDTO: WeatherDTO)
    fun onFailed(throwable: Throwable)
}