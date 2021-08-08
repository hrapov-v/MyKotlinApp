package superky.keytwo.mykotlinapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import superky.keytwo.mykotlinapp.R
import superky.keytwo.mykotlinapp.databinding.ActivityMainBinding
import superky.keytwo.mykotlinapp.databinding.MainActivityWebviewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
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

            Thread {
                var httpsURLConnection: HttpsURLConnection? = null
                try {
                    val url = URL(binding.url.text.toString())
                    httpsURLConnection = url.openConnection() as HttpsURLConnection
                    httpsURLConnection.requestMethod = "GET"
                    httpsURLConnection.connectTimeout = 5000

                    var reader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                    val result = reader.lines().collect(Collectors.joining("\n"))
                    runOnUiThread {
                        binding.webView.loadData(result, "text/html; charset = utf-8", "utf-8")
                    }
                } catch (e: Exception) {
                    Log.d("myLog", e.toString())
                    Log.d("myLog", e.localizedMessage)
                }
            }.start()
        }
    }

}