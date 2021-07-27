package superky.keytwo.mykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import superky.keytwo.mykotlinapp.model.Repository
import superky.keytwo.mykotlinapp.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    val repository: Repository = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getWeather() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        Thread {
            sleep(2000)
            liveDataObserver.postValue(AppState.Succes(repository.getWeatherFromLocal()))
        }.start()
    }
}