package superky.keytwo.mykotlinapp.model

import superky.keytwo.mykotlinapp.view.Weather

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocal(): Weather {
        return Weather()
    }


}