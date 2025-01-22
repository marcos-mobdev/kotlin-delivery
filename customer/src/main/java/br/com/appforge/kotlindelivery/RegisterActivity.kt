package br.com.appforge.kotlindelivery

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.core.LoadingAlert
import br.com.appforge.core.showMessage
import br.com.appforge.kotlindelivery.databinding.ActivityMainBinding
import br.com.appforge.kotlindelivery.databinding.ActivityRegisterBinding
import br.com.appforge.kotlindelivery.domain.model.User
import br.com.appforge.kotlindelivery.presentation.viewmodel.AuthenticationViewModel
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
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
        startActivity(Intent(this,MainActivity::class.java))
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

        authenticationViewModel.success.observe(this){success->
            if(success){
                //Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                navigateToMain()
            }else{
                showMessage("Error while registering user")
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
                authenticationViewModel.registerUser(user)
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