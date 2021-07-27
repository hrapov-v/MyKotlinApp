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
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.databinding.MainFragmentBinding
import superky.keytwo.mykotlinapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    var _binding: MainFragmentBinding? = null



    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val textView: TextView? = view.findViewById(R.id.test)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java) //потому что он на джава
        val observer = Observer<Any>{ Toast.makeText(context, "Worked", Toast.LENGTH_LONG).show() }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getDataFromLocalSource()

    }

}