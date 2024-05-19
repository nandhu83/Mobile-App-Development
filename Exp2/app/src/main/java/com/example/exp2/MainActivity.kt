package com.example.exp2
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var textViewInput: TextView
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.textViewInput)

        val buttonNumbers = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )

        val buttonOperators = listOf(
            R.id.buttonPlus, R.id.buttonMinus, R.id.buttonMultiply, R.id.buttonDivide
        )

        for (id in buttonNumbers) {
            findViewById<Button>(id).setOnClickListener { onDigit((it as Button).text.toString()) }
        }

        for (id in buttonOperators) {
            findViewById<Button>(id).setOnClickListener { onOperator((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClear() }
        findViewById<Button>(R.id.buttonEqual).setOnClickListener { onEqual() }
        findViewById<Button>(R.id.buttonDot).setOnClickListener { onDecimalPoint() }
    }

    private fun onDigit(digit: String) {
        if (stateError) {
            textViewInput.text = digit
            stateError = false
        } else {
            textViewInput.append(digit)
        }
        lastNumeric = true
    }

    private fun onOperator(operator: String) {
        if (lastNumeric && !stateError) {
            textViewInput.append(operator)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun onClear() {
        textViewInput.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    private fun onEqual() {
        if (lastNumeric && !stateError) {
            try {
                val result = evaluate(textViewInput.text.toString())
                textViewInput.text = result.toString()
                lastDot = true
            } catch (e: Exception) {
                textViewInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }

    private fun onDecimalPoint() {
        if (lastNumeric && !stateError && !lastDot) {
            textViewInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun evaluate(expression: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0

            fun nextChar() {
                ch = if (++pos < expression.length) expression[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < expression.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.toInt())) x += parseTerm()
                    else if (eat('-'.toInt())) x -= parseTerm()
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor()
                    else if (eat('/'.toInt())) x /= parseFactor()
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor()
                if (eat('-'.toInt())) return -parseFactor()

                var x: Double
                val startPos = pos
                if (eat('('.toInt())) {
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) {
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = expression.substring(startPos, pos).toDouble()
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }

                return x
            }
        }.parse()
    }
}

