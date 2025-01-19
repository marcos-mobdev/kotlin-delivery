package br.com.appforge.kotlindelivery

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.kotlindelivery.databinding.ActivityMainBinding
import br.com.appforge.kotlindelivery.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initializeToolbar()
    }

    private fun initializeToolbar() {
        val toolbar = binding.includeTbMain.tbMain
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            title = "Register User"
            setDisplayHomeAsUpEnabled(true)
        }
    }
}