package superky.keytwo.mykotlinapp.model

class Weather(val city: City = getDefaultCity(), val temperature: Int = 1, val feelsLike: Int = 0)

fun getDefaultCity() =
    City("Moscow", 55.75, 53.37)

data class City(val city: String, val lat: Double, val long: Double)
