package superky.keytwo.mykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import superky.keytwo.mykotlinapp.model.Repository
import superky.keytwo.mykotlinapp.model.RepositoryImpl
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    val repository: Repository = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getWeather() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        Thread {
            liveDataObserver.postValue(AppState.Loading)
            sleep(4000)
            liveDataObserver.postValue(AppState.Succes(repository.getWeatherFromLocal()))
        }.start()
    }
}