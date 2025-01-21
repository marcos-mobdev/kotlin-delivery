package br.com.appforge.kotlindelivery

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.appforge.kotlindelivery.databinding.ActivityMainBinding
import br.com.appforge.kotlindelivery.databinding.ActivityRegisterBinding
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        initializeClickEvents()
    }

    private fun initializeClickEvents() {
        with(binding){
            btnRegister.setOnClickListener {
                val name = editRegisterName.text.toString()
                val email = editRegisterEmail.text.toString()
                val password = editRegisterPassword.text.toString()
                val phone = editRegisterPhone.text.toString()

                val nameValidation = name.validator()
                    .minLength(6)
                    .maxLength(50)
                    .check()

                val emailValidation = email.validator()
                    .validEmail()
                    .check()

                val passwordValidation = password.validator()
                    .minLength(6)
                    .atleastOneUpperCase()
                    .atleastOneLowerCase()
                    .atleastOneNumber()
                    .check()

                val phoneValidation = phone.validator()
                    .minLength(14)
                    .check()

                Log.i("info_validation", "name($nameValidation) email($emailValidation) pass($passwordValidation) phone($phoneValidation) ")

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