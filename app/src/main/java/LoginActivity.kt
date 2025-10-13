package tn.esprit.gamerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import tn.esprit.gamerapp.databinding.ActivityLoginBinding
import tn.esprit.gamerapp.utils.Validator
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupValidation()
        setupClickListeners()
    }

    private fun setupValidation() {
        // Real-time email validation
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateEmail()
            }
        })

        // Real-time password validation
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
            }
        })
    }

    private fun validateEmail(): Boolean {
        val email = binding.etEmail.text.toString()
        return if (!Validator.isValidEmail(email)) {
            binding.etEmail.setBackgroundResource(R.drawable.input_background_error)
            binding.tvEmailError.visibility = android.view.View.VISIBLE
            false
        } else {
            binding.etEmail.setBackgroundResource(R.drawable.input_background_normal)
            binding.tvEmailError.visibility = android.view.View.GONE
            true
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.etPassword.text.toString()
        return if (!Validator.isValidPassword(password)) {
            binding.etPassword.setBackgroundResource(R.drawable.input_background_error)
            binding.tvPasswordError.visibility = android.view.View.VISIBLE
            false
        } else {
            binding.etPassword.setBackgroundResource(R.drawable.input_background_normal)
            binding.tvPasswordError.visibility = android.view.View.GONE
            true
        }
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                // Navigate to HomeScreen (to be created later)
                Snackbar.make(binding.root, "Login Successful!", Snackbar.LENGTH_SHORT).show()
                // TODO: startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Snackbar.make(binding.root, "You Have some errors in your inputs!", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.tvForgotPassword.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }

        binding.btnFacebook.setOnClickListener {
            Snackbar.make(binding.root, "Coming soon :)", Snackbar.LENGTH_SHORT).show()
        }

        binding.btnGoogle.setOnClickListener {
            Snackbar.make(binding.root, "Coming soon :)", Snackbar.LENGTH_SHORT).show()
        }

        binding.tvRegisterNow.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}