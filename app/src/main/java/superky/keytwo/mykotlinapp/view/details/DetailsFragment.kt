package superky.keytwo.mykotlinapp.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import superky.keytwo.mykotlinapp.databinding.FragmentDetailsBinding
import superky.keytwo.mykotlinapp.model.Weather
import superky.keytwo.mykotlinapp.model.WeatherDTO

class DetailsFragment : Fragment(), WeatherLoaderListener {

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


    override fun onLoaded(weatherDTO: WeatherDTO) {
        with(binding) {
            cityCoordinates.text = "${weatherLocal.city.lat} ${weatherLocal.city.lon}"
            cityName.text = weatherLocal.city.name
            feelsLikeValue.text = "${weatherDTO.fact.feels_like}"
            temperatureValue.text = "${weatherDTO.fact.temp}"
            condition.text = "${weatherDTO.fact.condition}"
        }
    }

    override fun onFailed(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }


    lateinit var weatherLocal: Weather

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_WEATHER)?.apply {
            weatherLocal = this
            WeatherLoader(this@DetailsFragment, city.lat, city.lon).loadWeather()
        }
    }

}