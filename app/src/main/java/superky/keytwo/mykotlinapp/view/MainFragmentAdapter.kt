package superky.keytwo.mykotlinapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.model.Weather

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder> {

    private var weatherData: List<Weather> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view: View =
            (LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_main_recycler_item, parent, false
            )) as View
        return MainViewHolder(view)
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}