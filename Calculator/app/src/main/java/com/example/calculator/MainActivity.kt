package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private var firstOperand = Double.NaN
    private var currentOperator = ""
    private var isTypingNewNumber = true
    private val decimalFormat = DecimalFormat("#.##########")

    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        val themeKey = ThemeHelper.getThemeKey(this)
        setTheme(ThemeHelper.getThemeResId(themeKey))

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<ImageView>(R.id.ivSettings).setOnClickListener {
            val themePicker = ThemePickerFragment()
            themePicker.show(supportFragmentManager, "ThemePicker")
        }

        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)

        setupCalculatorLogic()
    }

    private fun setupCalculatorLogic() {
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, 
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { view ->
                val button = view as Button
                if (isTypingNewNumber) {
                    tvResult.text = button.text
                    isTypingNewNumber = false
                } else {
                    if (tvResult.text.toString() == "0") {
                        tvResult.text = button.text
                    } else {
                        tvResult.append(button.text)
                    }
                }
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener {
            if (isTypingNewNumber) {
                tvResult.text = "0."
                isTypingNewNumber = false
            } else if (!tvResult.text.contains(".")) {
                tvResult.append(".")
            }
        }

        findViewById<Button>(R.id.btnAC).setOnClickListener {
            firstOperand = Double.NaN
            currentOperator = ""
            tvResult.text = "0"
            tvExpression.text = ""
            isTypingNewNumber = true
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            if (tvResult.text.isNotEmpty() && !isTypingNewNumber) {
                val newText = tvResult.text.dropLast(1)
                tvResult.text = if (newText.isEmpty() || newText == "-") "0" else newText
                if (tvResult.text == "0") isTypingNewNumber = true
            }
        }

        val operatorButtons = mapOf(
            R.id.btnPlus to "+",
            R.id.btnMinus to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )

        for ((id, operator) in operatorButtons) {
            findViewById<Button>(id).setOnClickListener {
                if (!isTypingNewNumber || !firstOperand.isNaN()) {
                    evaluateCurrentExpression()
                }
                
                try {
                    firstOperand = tvResult.text.toString().toDouble()
                } catch (e: Exception) {
                    firstOperand = 0.0
                }
                
                currentOperator = operator
                tvExpression.text = "${decimalFormat.format(firstOperand)} $currentOperator"
                isTypingNewNumber = true
            }
        }

        findViewById<Button>(R.id.btnPercent).setOnClickListener {
            try {
                val currentValue = tvResult.text.toString().toDouble()
                tvResult.text = decimalFormat.format(currentValue / 100)
                isTypingNewNumber = true
                // If there's an active operator, optionally you might append percentage to expression, but simplest is keeping as is
            } catch (e: Exception) {}
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            val secondOperandText = tvResult.text.toString()
            evaluateCurrentExpression()
            
            // Re-show full calculation in expression text if not NaN
            if (currentOperator.isNotEmpty()) {
                 tvExpression.text = "${decimalFormat.format(firstOperand)} $currentOperator $secondOperandText ="
            } else {
                 tvExpression.text = ""
            }

            currentOperator = ""
            isTypingNewNumber = true
        }
    }

    private fun evaluateCurrentExpression() {
        if (!firstOperand.isNaN() && currentOperator.isNotEmpty()) {
            try {
                val secondOperand = tvResult.text.toString().toDouble()
                var result = 0.0
                when (currentOperator) {
                    "+" -> result = firstOperand + secondOperand
                    "-" -> result = firstOperand - secondOperand
                    "*" -> result = firstOperand * secondOperand
                    "/" -> {
                        if (secondOperand != 0.0) {
                            result = firstOperand / secondOperand
                        } else {
                            tvResult.text = "Error"
                            tvExpression.text = ""
                            firstOperand = Double.NaN
                            currentOperator = ""
                            isTypingNewNumber = true
                            return
                        }
                    }
                }
                tvResult.text = decimalFormat.format(result)
                firstOperand = result
            } catch (e: Exception) {
                // Ignore parsing errors
            }
        }
    }
}