package com.ds.tip_time

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.ds.tip_time.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listener de clique no botão Calcular
        binding.calculateButton.setOnClickListener { calculateTip() }

        //Listener no EditText que espera pela tecla enter ser pressionada
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    //Método que calcula a gorjeta
    private fun calculateTip() {

        //Variavel que armazena o atributo de texto de EditText e o converde do tipo Editable para String
        val stringInTextField = binding.costOfServiceEditText.text.toString()

        //Variavel que armazena a variavel stringInTextField e a transforma no tipo Double
        val cost = stringInTextField.toDouble()

        //Acessa o atributo checkedRadioButton do RadioGroup

        //Acessando a porcentagem da gorjeta
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Cálculo da gorjeta
        var tip = tipPercentage * cost

        //Arredondar gorjeta
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        //Formatação em moeda da gorjeta
        val formatedTip = NumberFormat.getCurrencyInstance().format(tip)

        //Exibindo o valor da gorjeta
        binding.tipResult.text = getString(R.string.total_da_gorjeta, formatedTip)
    }

    // Key Listener para esconder o teclado quando a tecla "enter" for apertada.
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