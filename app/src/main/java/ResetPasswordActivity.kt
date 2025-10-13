package tn.esprit.gamerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tn.esprit.gamerapp.databinding.ActivityResetPasswordBinding
import tn.esprit.gamerapp.utils.Validator

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupValidation()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupValidation() {
        // Real-time password validation
        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validatePassword()
                if (binding.etConfirmPassword.text.isNotEmpty()) {
                    validateConfirmPassword()
                }
            }
        })

        // Real-time confirm password validation
        binding.etConfirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateConfirmPassword()
            }
        })
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

    private fun validateConfirmPassword(): Boolean {
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        return if (!Validator.doPasswordsMatch(password, confirmPassword)) {
            binding.etConfirmPassword.setBackgroundResource(R.drawable.input_background_error)
            binding.tvConfirmPasswordError.visibility = android.view.View.VISIBLE
            false
        } else {
            binding.etConfirmPassword.setBackgroundResource(R.drawable.input_background_normal)
            binding.tvConfirmPasswordError.visibility = android.view.View.GONE
            true
        }
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            if (validatePassword() && validateConfirmPassword()) {
                // Navigate to LoginScreen and clear back stack
                Snackbar.make(binding.root, "Password Reset Successful!", Snackbar.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(binding.root, "You Have some errors in your inputs!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}