package superky.keytwo.mykotlinapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import superky.keytwo.mykotlinapp.databinding.FragmentDetailsBinding
import superky.keytwo.mykotlinapp.model.Weather

class DetailsFragment : Fragment() {

    companion object {
        val KEY_WEATHER: String = "key"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    var _binding: FragmentDetailsBinding? = null
    val binding: FragmentDetailsBinding
        get(): FragmentDetailsBinding {
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val weather: Weather? = arguments?.getParcelable(KEY_WEATHER)
        weather?.let { setData(weather) }
    }

    private fun setData(weather: Weather) {
        //рещили на уроке что with лучше потому что apply вернёт в пустоту копию
        with(binding) {
            with(weather) {
                cityCoordinates.text =
                    "${city.lat} ${city.long}"
                cityName.text = city.name
                feelsLikeValue.text = feelsLike.toString()
                temperatureValue.text = temperature.toString()
            }
        }

        /*binding.apply {
            cityCoordinates.text =
                "${weather.city.lat} ${weather.city.long}"
            cityName.text = weather.city.city
            feelsLikeValue.text = weather.feelsLike.toString()
            temperatureValue.text = weather.temperature.toString()
        }*/

    }
}