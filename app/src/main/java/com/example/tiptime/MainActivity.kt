package com.example.tiptime

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        /*Add the bellow code for setting up the key listener on the text field within the activity's onCreate() method.
        This is because you want your key listener to be attached as soon as the layout is created and before the user starts interacting with the activity.
        */
        binding.costOfService.setOnKeyListener { v, keyCode, event ->handleKeyEvent(v, keyCode) }
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
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}

