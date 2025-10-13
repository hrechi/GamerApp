package tn.esprit.gamerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tn.esprit.gamerapp.databinding.ActivityForgotPasswordBinding
import tn.esprit.gamerapp.utils.Validator

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding
    private val EMAIL_CODE = "1234"
    private val SMS_CODE = "6789"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
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
        // Real-time email/phone validation
        binding.etEmailPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                validateEmailPhone()
            }
        })
    }

    private fun validateEmailPhone(): Boolean {
        val input = binding.etEmailPhone.text.toString()
        return if (!Validator.isValidEmailOrPhone(input)) {
            binding.etEmailPhone.setBackgroundResource(R.drawable.input_background_error)
            binding.tvEmailPhoneError.visibility = android.view.View.VISIBLE
            false
        } else {
            binding.etEmailPhone.setBackgroundResource(R.drawable.input_background_normal)
            binding.tvEmailPhoneError.visibility = android.view.View.GONE
            true
        }
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            if (validateEmailPhone()) {
                // Navigate to OTP screen with email code
                navigateToOTP(EMAIL_CODE)
            } else {
                Snackbar.make(binding.root, "You Have some errors in your inputs!", Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.btnSendSMS.setOnClickListener {
            if (validateEmailPhone()) {
                // Navigate to OTP screen with SMS code
                navigateToOTP(SMS_CODE)
            } else {
                Snackbar.make(binding.root, "You Have some errors in your inputs!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToOTP(code: String) {
        val intent = Intent(this, OTPValidationActivity::class.java)
        intent.putExtra("OTP_CODE", code)
        startActivity(intent)
    }
}