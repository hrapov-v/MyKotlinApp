package superky.keytwo.mykotlinapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveDataObserver: MutableLiveData<Any> = MutableLiveData()) :
    ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getDataFromLocalSource() {
        Thread{
            sleep(1000)
            liveDataObserver.postValue(Any())
        }
    }
}