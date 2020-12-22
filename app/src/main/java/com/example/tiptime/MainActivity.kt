package com.example.tiptime

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateBtn.setOnClickListener{
            calculateTip()
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        val cost=binding.costOfService.text.toString().toDoubleOrNull()
        if(cost==null)
        {
            binding.tipRsult.text=""
            return
        }
        val selectedId=binding.options.checkedRadioButtonId
        val tipPercentage=when(selectedId){
            R.id.option_twenty_percent ->0.2
            R.id.option_eighteen_percent ->0.18
            else -> 0.15
        }
        var tip=tipPercentage*cost
        if(binding.roundUpId.isChecked){
            tip=kotlin.math.ceil(tip)
        }
        displayTip(tip)

    }

    private fun displayTip(tip: Double) {
        val formattedTip=NumberFormat.getCurrencyInstance().format(tip)
        binding.tipRsult.text = "Tip Amount $formattedTip"
    }
}