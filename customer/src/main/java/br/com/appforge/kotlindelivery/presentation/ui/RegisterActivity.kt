package br.com.appforge.kotlindelivery.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.appforge.core.LoadingAlert
import br.com.appforge.core.UIState
import br.com.appforge.core.navigateTo
import br.com.appforge.core.showMessage
import br.com.appforge.kotlindelivery.R
import br.com.appforge.kotlindelivery.databinding.ActivityRegisterBinding
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.presentation.viewmodel.AuthenticationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private val binding by lazy{
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private val authenticationViewModel:AuthenticationViewModel by viewModels()

    private val loadingAlert by lazy {
        LoadingAlert(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initializeToolbar()
        initializeClickEvents()
        initializeObservables()
    }

    fun navigateToMain(){
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun initializeObservables() {
        authenticationViewModel.validationResult.observe(this){ validationResult ->
            with(binding){
                editRegisterName.error = if(validationResult.name) null else getString(R.string.error_register_name)
                editRegisterEmail.error = if(validationResult.email) null else getString(R.string.error_register_email)
                editRegisterPassword.error = if(validationResult.password) null else getString(R.string.error_register_password)
                editRegisterPhone.error = if(validationResult.phone) null else getString(R.string.error_register_phone)
            }
        }

        authenticationViewModel.isLoading.observe(this){isLoading->
            if(isLoading){
                loadingAlert.show("Registering your profile...")

            }else{
               loadingAlert.close()
            }
        }
    }

    private fun initializeClickEvents() {
        with(binding){
            btnRegister.setOnClickListener {
                val name = editRegisterName.text.toString()
                val email = editRegisterEmail.text.toString()
                val password = editRegisterPassword.text.toString()
                val phone = editRegisterPhone.text.toString()

                val user = User(
                    email,password,name,phone
                )
                authenticationViewModel.registerUser(user){ uistate ->
                    when(uistate){
                        is UIState.Success -> {
                            navigateTo(MainActivity::class.java)
                        }
                        is UIState.Error ->{
                            showMessage(uistate.errorMessage)
                        }
                    }
                }
            }
        }
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