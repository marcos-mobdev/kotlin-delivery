package br.com.appforge.kotlindelivery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.kotlindelivery.databinding.ActivityLoginBinding
import br.com.appforge.kotlindelivery.databinding.ActivityMainBinding
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.presentation.viewmodel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val authenticationViewModel:AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initializeClickEvents()
        initializeObservables()
    }

    fun navigateToMain(){
        startActivity(Intent(this,MainActivity::class.java))
    }

    private fun initializeObservables() {
        authenticationViewModel.validationResult.observe(this){ validationResult ->
            with(binding){
                editLoginEmail.error = if(validationResult.email) null else getString(R.string.error_register_email)
                editLoginPassword.error = if(validationResult.password) null else getString(R.string.error_register_password)
            }
        }
        authenticationViewModel.success.observe(this){success->
            if(success){
                //Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                navigateToMain()
            }else{
                Toast.makeText(this, "Login error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeClickEvents() {

        with(binding){
            textRegister.setOnClickListener {
                startActivity(Intent(applicationContext, RegisterActivity::class.java))
            }
            btnLogin.setOnClickListener {
                val email = editLoginEmail.text.toString()
                val password = editLoginPassword.text.toString()
                val user = User(
                    email = email,
                    password = password
                )
                authenticationViewModel.loginUser(user)
            }
        }

    }
}