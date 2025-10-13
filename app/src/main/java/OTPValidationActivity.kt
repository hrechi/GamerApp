package tn.esprit.gamerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tn.esprit.gamerapp.databinding.ActivityOtpValidationBinding

class OTPValidationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpValidationBinding
    private var expectedCode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the expected OTP code from intent
        expectedCode = intent.getStringExtra("OTP_CODE") ?: "1234"

        setupToolbar()
        setupOTPInputs()
        setupClickListeners()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupOTPInputs() {
        val otpFields = listOf(
            binding.etOtp1,
            binding.etOtp2,
            binding.etOtp3,
            binding.etOtp4
        )

        // Auto-focus next field
        otpFields.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && index < otpFields.size - 1) {
                        // Move to next field
                        otpFields[index + 1].requestFocus()
                    }
                }
            })

            // Handle backspace to move to previous field
            editText.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (editText.text.isEmpty() && index > 0) {
                        otpFields[index - 1].requestFocus()
                        return@setOnKeyListener true
                    }
                }
                false
            }
        }

        // Auto-focus first field
        binding.etOtp1.requestFocus()
    }

    private fun setupClickListeners() {
        binding.btnVerify.setOnClickListener {
            val enteredCode = getEnteredOTP()

            if (enteredCode.length != 4) {
                Snackbar.make(binding.root, "Please enter complete code!", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredCode == expectedCode) {
                // Navigate to Reset Password Screen
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "Wrong code!", Snackbar.LENGTH_SHORT).show()
                clearOTPFields()
            }
        }

        binding.tvResendCode.setOnClickListener {
            Snackbar.make(binding.root, "Coming soon :)", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun getEnteredOTP(): String {
        return "${binding.etOtp1.text}${binding.etOtp2.text}${binding.etOtp3.text}${binding.etOtp4.text}"
    }

    private fun clearOTPFields() {
        binding.etOtp1.text.clear()
        binding.etOtp2.text.clear()
        binding.etOtp3.text.clear()
        binding.etOtp4.text.clear()
        binding.etOtp1.requestFocus()
    }
}