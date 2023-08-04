package com.example.alculatorv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.alculatorv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var operand1: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn7.setOnClickListener { appendDigit(7) }
        binding.btn8.setOnClickListener { appendDigit(8) }
        binding.btn9.setOnClickListener { appendDigit(9) }
        binding.btn4.setOnClickListener { appendDigit(4) }
        binding.btn5.setOnClickListener { appendDigit(5) }
        binding.btn6.setOnClickListener { appendDigit(6) }
        binding.btn1.setOnClickListener { appendDigit(1) }
        binding.btn2.setOnClickListener { appendDigit(2) }
        binding.btn3.setOnClickListener { appendDigit(3) }
        binding.btn0.setOnClickListener { appendDigit(0) }

        binding.clear.setOnClickListener {
            operand1 = null
            pendingOperation = "="
            binding.resultTextView.text = ""
        }

        binding.buttonEquals.setOnClickListener {
            val operand2 = binding.resultTextView.text.toString().toDouble()
            val result = performOperation(pendingOperation, operand1, operand2)
            operand1 = result
            pendingOperation = "="
            binding.resultTextView.text = result.toString()
        }

        binding.buttonPlus.setOnClickListener {
            setOperator("+")
        }
        binding.buttonMinus.setOnClickListener {
            setOperator("-")
        }
        binding.multiplicationButton.setOnClickListener {
            setOperator("*")
        }
        binding.divisionButton.setOnClickListener {
            setOperator("/")
        }
    }

    private fun appendDigit(digit: Int) {
        binding.resultTextView.append(digit.toString())
    }

    private fun setOperator(operator: String) {
        if (binding.resultTextView.text.isNotEmpty()) {
            operand1 = binding.resultTextView.text.toString().toDouble()
            pendingOperation = operator
            binding.resultTextView.text = ""
        }
    }

    private fun performOperation(operation: String, operand1: Double?, operand2: Double) : Double {
        return when (operation) {
            "+" -> operand1?.plus(operand2) ?: operand2
            "-" -> operand1?.minus(operand2) ?: operand2
            "*" -> operand1?.times(operand2) ?: operand2
            "/" -> operand1?.div(operand2) ?: operand2
            else -> operand1 ?: operand2
        }
    }
}