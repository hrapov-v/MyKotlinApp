package superky.keytwo.mykotlinapp.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import superky.keytwo.mykotlinapp.model.Weather

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder> {

    private var weatherData : List<Weather> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val mainViewHolder : MainViewHolder
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}