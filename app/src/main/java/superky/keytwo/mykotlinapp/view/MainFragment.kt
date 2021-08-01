package superky.keytwo.mykotlinapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.databinding.FragmentMainBinding
import superky.keytwo.mykotlinapp.model.Weather
import superky.keytwo.mykotlinapp.viewmodel.AppState
import superky.keytwo.mykotlinapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    val mainFragmentAdapter: MainFragmentAdapter =
        MainFragmentAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(weather: Weather) {
                val manager = activity?.supportFragmentManager
                if (manager != null){

                    val bundle= Bundle()
                    bundle.putParcelable(DetailsFragment.KEY_WEATHER, weather)
                    //Вот здесь в методичке add
                    manager.beginTransaction().replace(R.id.container, DetailsFragment.newInstance(bundle)).
                    addToBackStack("").commitAllowingStateLoss()
                }
            }
        })

    lateinit var viewModel: MainViewModel

    //Как выяснилось без костыля не работает потому что есть onDestroy()
    var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
        get(): FragmentMainBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mainFragmentAdapter.removeListener()
    }


    //Вот тут точно кроется ошибка какая то как мне кажется
    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
        //Старая запись
        /*val view = inflater.inflate(R.layout.main_fragment, container, false)
        val textView: TextView? = view.findViewById(R.id.test)

        return view*/
    }

    var isRussian: Boolean = true
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.mainFragmentFAB.setOnClickListener {
            initListenner()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) //потому что он на джава
        //Было для теста//val observer = Observer<Any> { Toast.makeText(context, "Worked", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        //binding.test.text = "TEXT" //Можно так
        viewModel.getWeatherFromLocalSourceRussian()
        binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        isRussian = true
    }

    private fun initListenner() {
        if (isRussian) {
            viewModel.getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceRussian()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isRussian = !isRussian
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Succes -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                binding.mainFragmentRecyclerView.adapter = mainFragmentAdapter
                mainFragmentAdapter.setWeather(appState.dataWeather)
                /*Snackbar.make(binding.mainView, "Загружено", Snackbar.LENGTH_LONG).show()
                setData(appState)*/
            }
            is AppState.Error -> TODO() //вывести ошибку
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
        }
    }

}