package superky.keytwo.mykotlinapp.model

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalRussian(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalWorld(): List<Weather> {
        return getWorldCities()
    }


}