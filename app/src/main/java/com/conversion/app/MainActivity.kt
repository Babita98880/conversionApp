package com.conversion.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var conversionSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var outputTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize views
        inputEditText = findViewById(R.id.inputEditText)
        conversionSpinner = findViewById(R.id.conversionSpinner)
        convertButton = findViewById(R.id.convertButton)
        outputTextView = findViewById(R.id.outputTextView)

        // Set up the spinner with conversion options
        val conversionOptions = arrayOf("Centimeters to Inches", "Inches to Centimeters", "Miles to Kilometers", "Kilometers to Miles", "Pounds to Kilograms", "Kilograms to Pounds")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionSpinner.adapter = adapter

        // Set up the click listener for the convert button
        convertButton.setOnClickListener { convertValue() }
    }

    private fun convertValue() {
        val inputValue = inputEditText.text.toString().toDoubleOrNull()

        if (inputValue != null) {
            val selectedConversion = conversionSpinner.selectedItemPosition
            val result = when (selectedConversion) {
                0 -> convertCmToIn(inputValue)
                1 -> convertInToCm(inputValue)
                2 -> convertMilesToKm(inputValue)
                3 -> convertKmToMiles(inputValue)
                4 -> convertPoundsToKg(inputValue)
                5 -> convertKgToPounds(inputValue)
                else -> 0.0
            }
            outputTextView.text = "Result: $result"
        } else {
            outputTextView.text = "Invalid input"
        }
    }

    private fun convertCmToIn(cm: Double): Double {
        return cm / 2.54
    }

    private fun convertInToCm(inches: Double): Double {
        return inches * 2.54
    }

    private fun convertMilesToKm(miles: Double): Double {
        return miles * 1.60934
    }

    private fun convertKmToMiles(km: Double): Double {
        return km / 1.60934
    }

    private fun convertPoundsToKg(pounds: Double): Double {
        return pounds * 0.453592
    }

    private fun convertKgToPounds(kg: Double): Double {
        return kg / 0.453592
    }
}
