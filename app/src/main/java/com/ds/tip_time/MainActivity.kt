package com.ds.tip_time

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ds.tip_time.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Listener de clique no botão Calcular
        binding.calculateButton.setOnClickListener { calculateTip() }
    }
    //Método que calcula a gorjeta
    fun calculateTip(){

        //Variavel que armazena o atributo de texto de EditText e o converde do tipo Editable para String
        val stringInTextField = binding.costOfService.text.toString()

        //Variavel que armazena a variavel stringInTextField e a transforma no tipo Double
        val cost = stringInTextField.toDouble()

        //Acessa o atributo checkedRadioButton do RadioGroup
        val selectedId = binding.tipOptions.checkedRadioButtonId

        //Acessando a porcentagem da gorjeta
        val tipPercentage = when(selectedId){
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //Cálculo da gorjeta
        var tip = tipPercentage * cost

        //Arredondar gorjeta
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp){
            tip = kotlin.math.ceil(tip)
        }

        //Formatação em moeda da gorjeta
        val formatedTip = NumberFormat.getCurrencyInstance().format(tip)
    }
}