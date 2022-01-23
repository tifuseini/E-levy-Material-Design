package com.example.e_levy

import com.example.e_levy.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateElevy() }
    }

    private fun calculateElevy() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.charges.text = ""
            return
        }

        val tipPercentage = when (binding.networkOptions.checkedRadioButtonId) {
            R.id.option_mtn -> 0.0275
            R.id.option_vodafone -> 0.0175
            else -> 0.0175
        }

        val tip = tipPercentage * cost

        val formattedTip = NumberFormat.getInstance().format(tip)
        binding.charges.text = getString(R.string.charges, formattedTip)

        val recipient = cost - tip

        val formattedRecipientAmount = NumberFormat.getInstance().format(recipient)
        binding.recipientAmount.text = getString(R.string.recipient_amount,formattedRecipientAmount)


    }
}