package superky.keytwo.mykotlinapp.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import superky.keytwo.mykotlinapp.databinding.FragmentDetailsBinding
import superky.keytwo.mykotlinapp.model.FactDTO
import superky.keytwo.mykotlinapp.model.Weather
import superky.keytwo.mykotlinapp.model.WeatherDTO

class DetailsFragment : Fragment() {

    //здесь сейчас сама погода детали так сказать

    companion object {
        const val KEY_WEATHER: String = "key"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() : FragmentDetailsBinding {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    /*override fun onLoaded(weatherDTO: WeatherDTO) {
        with(binding) {
            cityCoordinates.text = "${weatherLocal.city.lat} ${weatherLocal.city.lon}"
            cityName.text = weatherLocal.city.name
            feelsLikeValue.text = "${weatherDTO.fact.feels_like}"
            temperatureValue.text = "${weatherDTO.fact.temp}"
            condition.text = "${weatherDTO.fact.condition}"
        }
    }

    //s
    override fun onFailed(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }*/

    private val loadResultReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            intent?.let {
                when (it.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                    DETAILS_RESPONSE_SUCCESS_EXTRA ->
                        renderData(
                            WeatherDTO(
                                FactDTO(
                                    it.getIntExtra(DETAILS_TEMP_EXTRA, -1),
                                    it.getIntExtra(DETAILS_FEELS_LIKE_EXTRA, -1),
                                    it.getStringExtra(DETAILS_CONDITION_EXTRA)!!
                                )
                            )
                        )
                    else -> null
                }
            }
        }
    }

    fun renderData(weather: WeatherDTO) {
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayout.visibility = View.GONE

        weatherBundle?.let {
            with(binding) {
                cityCoordinates.text = "${it.city.lat} ${it.city.lon}"
                cityName.text = it.city.name
                feelsLikeValue.text = weather.fact.temp.toString()
                temperatureValue.text = weather.fact.feels_like.toString()
                condition.text = weather.fact.condition
            }
        }
    }

    var weatherBundle: Weather? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(
                loadResultReceiver, IntentFilter(DETAILS_INTENT_FILTER)
            )
        }
    }

    fun getWeather() {
        binding.mainView.visibility = View.GONE
        binding.loadingLayout.visibility = View.VISIBLE
        weatherBundle?.let {
            context?.startService(Intent(context, DetailsService::class.java).apply {
                putExtra(LATITUDE_EXTRA, it.city.lat)
                putExtra(LONGITUDE_EXTRA, it.city.lon)
            })
        }
    }

    lateinit var weatherLocal: Weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_WEATHER)?.apply {
            weatherBundle = this
            //WeatherLoader(this@DetailsFragment, city.lat, city.lon).loadWeather() //Устаревшее
            getWeather()
        }
    }

}