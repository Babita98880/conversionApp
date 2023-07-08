package com.conversion.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import java.math.BigDecimal
import java.math.RoundingMode
class MainActivity : AppCompatActivity() {
    private lateinit var inputEditText: EditText
    private lateinit var conversionFromSpinner: Spinner
    private lateinit var conversionToSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var outputTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize views
        inputEditText = findViewById(R.id.inputEditText)
        conversionFromSpinner = findViewById(R.id.conversionFromSpinner)
        conversionToSpinner = findViewById(R.id.conversionToSpinner)
        convertButton = findViewById(R.id.convertButton)
        outputTextView = findViewById(R.id.outputTextView)

        // Set up the spinner with conversion options
        val conversionFromOptions = arrayOf("Centimeters", "Inches", "Miles", "Kilometers", "Meters")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionFromOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionFromSpinner.adapter = adapter
        val conversionToOptions = arrayOf("Millimeters", "Centimeters", "Kilometers" ,"Miles", "Inches")
         var adapterForTo = ArrayAdapter(this, android.R.layout.simple_spinner_item, conversionToOptions)
        adapterForTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        conversionToSpinner.adapter = adapterForTo
        // Set up the click listener for the convert button
        convertButton.setOnClickListener { convertValue() }
    }

    private fun convertValue() {
        val inputValue = inputEditText.text.toString().toDoubleOrNull()

        if (inputValue != null) {
            val currentUnit = conversionFromSpinner.selectedItem.toString()
            val targetUnit = conversionToSpinner.selectedItem.toString()
            val result = when (currentUnit) {
                "Centimeters" -> convertCentimetersTo(targetUnit, inputValue)
                "Inches" -> convertInchesTo(targetUnit, inputValue)
                "Kilometers" -> convertKilometersTo(targetUnit, inputValue)
                "Miles" -> convertMilesTo(targetUnit, inputValue)
                "Meters" -> convertMetersTo(targetUnit, inputValue)

                else -> 0.0
            }
            var convertDoubleinputValue = inputValue.toInt();
            outputTextView.text = "$convertDoubleinputValue $currentUnit = $result $targetUnit"
    } else {
            outputTextView.text = "Please enter the convert value !"
        }
    }

    private fun convertKgToPounds(kg: Double): Double {
        return kg / 0.453592
    }
    private fun convertCentimetersTo(unit: String, value: Double): Double {
        return when (unit) {
            "Centimeters" -> value
            "Inches" -> value / 2.54
            "Kilometers" -> value / 100000
            "Miles" -> value / 160934
            "Millimeters" -> value * 10
            else -> 0.0
        }
    }
    private fun convertInchesTo(unit: String, value: Double): Double {
        return when (unit) {
            "Centimeters" -> value * 2.54
            "Inches" -> value
            "Kilometers" -> value / 39370.08
            "Miles" -> value / 63360
            "Millimeters" -> value * 25.4
            else -> 0.0
        }
    }
    private fun convertMilesTo(unit: String, value: Double): Double {
        return when (unit) {
            "Centimeters" -> value * 160934.4
            "Inches" -> value * 63360
            "Kilometers" -> value * 1.609344
            "Miles" -> value
            "Millimeters" -> value * 1609344.87
            else -> 0.0
        }
    }
    private fun convertKilometersTo(unit: String, value: Double): Double {
        return when (unit) {
            "Centimeters" -> value * 100000
            "Inches" -> value * 39370.08
            "Kilometers" -> value
            "Miles" -> value * 0.6213712
            "Millimeters" -> value * 1000000
            else -> 0.0
        }
    }

    private fun convertMetersTo(unit: String, value: Double): Double {
        return when (unit) {
            "Centimeters" -> value * 100
            "Inches" -> value / 0.0254
            "Kilometers" -> value / 1000
            "Miles" -> value /  1609.344
            "Millimeters" -> value * 1000
            else -> 0.0
        }
    }
    fun roundToTwoDecimalPlaces(value: Double): Double {
        val bigDecimal = BigDecimal(value).setScale(2, RoundingMode.HALF_UP)
        return bigDecimal.toDouble()
    }
}
