package superky.keytwo.mykotlinapp.view

import superky.keytwo.mykotlinapp.model.Weather

interface OnItemViewClickListener {
    fun onItemViewClick(weather: Weather)
}