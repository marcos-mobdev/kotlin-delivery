package br.com.appforge.kotlindelivery.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.appforge.core.LoadingAlert
import br.com.appforge.core.UIState
import br.com.appforge.core.navigateTo
import br.com.appforge.core.showMessage
import br.com.appforge.kotlindelivery.R
import br.com.appforge.kotlindelivery.databinding.ActivityLoginBinding
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.presentation.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val authenticationViewModel:AuthenticationViewModel by viewModels()

    private val loadingAlert by lazy {
        LoadingAlert(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition{
            //Check if user is logged in
            val loggedUser = authenticationViewModel.checkUserLoggedIn()
            //result
            if(loggedUser){
                navigateTo(MainActivity::class.java)
            }
            false
        }

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
        FirebaseAuth.getInstance().signOut()
    }

    override fun onStart() {
        super.onStart()
        authenticationViewModel.checkUserLoggedIn()
    }

    private fun initialize() {
        initializeClickEvents()
        initializeObservables()
    }

    private fun initializeObservables() {
        authenticationViewModel.validationResult.observe(this){ validationResult ->
            with(binding){
                editLoginEmail.error = if(validationResult.email) null else getString(R.string.error_register_email)
                editLoginPassword.error = if(validationResult.password) null else getString(R.string.error_register_password)
            }
        }

        authenticationViewModel.isLoading.observe(this){isLoading->
            if(isLoading){
                loadingAlert.show("Logging in with your credentials...")

            }else{
                loadingAlert.close()
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
                authenticationViewModel.loginUser(user){ uistatus ->
                    when(uistatus){
                        is UIState.Success -> {
                            navigateTo(MainActivity::class.java)
                        }
                        is UIState.Error ->{
                            showMessage(uistatus.errorMessage)
                        }
                    }

                }
            }
        }

    }
}