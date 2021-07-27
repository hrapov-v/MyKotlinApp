package superky.keytwo.mykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData()) :
    ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getWeather() = getDataFromLocalSource()

    fun getDataFromLocalSource() {
        Thread{
            sleep(1000)
            //liveDataObserver.postValue(AppState.Succes())
        }.start()
    }
}