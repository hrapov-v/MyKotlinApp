package superky.keytwo.mykotlinapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.model.Weather

class MainFragmentAdapter constructor(var onItemViewClickListener: OnItemViewClickListener?) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    //private var weatherData: List<Weather> = listOf()
    private lateinit var weatherData: List<Weather>

    fun setWeather(list: List<Weather>) {
        weatherData = list
        notifyDataSetChanged()
    }

    fun removeListener(){
        onItemViewClickListener = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            (LayoutInflater.from(parent.context).inflate(
                R.layout.fragment_main_recycler_item, parent, false
            ))
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.init(weatherData[position])
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun init(weather: Weather) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                weather.city.city
        }
    }
}