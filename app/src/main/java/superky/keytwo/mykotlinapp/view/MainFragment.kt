package superky.keytwo.mykotlinapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.databinding.MainFragmentBinding
import superky.keytwo.mykotlinapp.viewmodel.AppState
import superky.keytwo.mykotlinapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel

    //Возможно работает без костыля
    var _binding: MainFragmentBinding? = null
    val binding: MainFragmentBinding
        get(): MainFragmentBinding {
            return _binding!!
        }


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
        //Старая запись
        /*val view = inflater.inflate(R.layout.main_fragment, container, false)
        val textView: TextView? = view.findViewById(R.id.test)

        return view*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) //потому что он на джава
        //Было для теста//val observer = Observer<Any> { Toast.makeText(context, "Worked", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getWeather()
        //binding.test.text = "TEXT" //Можно так

    }

    private fun renderData(appState: AppState) {
        when (appState){
            is AppState.Succes -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.mainView,"Succes", Snackbar.LENGTH_LONG).show()
                setData(appState)
            }
            is AppState.Error -> TODO() //вывести ошибку
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun setData(appState: AppState.Succes) {
        binding.cityCoordinates.text =
            "${appState.dataWeather.city.lat} ${appState.dataWeather.city.long}"
        binding.cityName.text = appState.dataWeather.city.city
        binding.feelsLikeValue.text = appState.dataWeather.feelsLike.toString()
        binding.temperatureValue.text = appState.dataWeather.temperature.toString()
    }

}