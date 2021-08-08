package superky.keytwo.mykotlinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.databinding.ActivityMainBinding
import superky.keytwo.mykotlinapp.databinding.MainActivityWebviewBinding
import java.lang.Exception
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {
    lateinit var binding: MainActivityWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener(clickListener)
    }

    var clickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            var httpsURLConnection: HttpsURLConnection? = null
            try {
             val url = binding.url.text.toString()
            }catch (e: Exception){

            }
        }
    }

}