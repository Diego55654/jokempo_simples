package com.example.jokenpo_diego

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jokenpo_diego.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//Faz o botao receber o jogador por meio de um numero
        binding.btnPedra.setOnClickListener {
            binding.idResult.text = jogada(0)
        }
        binding.btnPapel.setOnClickListener {
            binding.idResult.text = jogada(1)
        }
        binding.btnTesoura.setOnClickListener {
            binding.idResult.text = jogada(2)
        }
    }
//Funcao principal que define a logica do jogo
    private fun jogada(num: Int): String {
        binding.idComput.visibility = View.VISIBLE

        //Garante o equilibrio do jogo
        val resultado = when (num) {
            0 -> listOf(0, 1, 2).random()  // Jogada do jogador é Pedra
            1 -> listOf(1, 2, 0).random()  // Jogada do jogador é Papel
            2 -> listOf(2, 0, 1).random()  // Jogada do jogador é Tesoura
            else -> 0
        }

        // Define a imagem correspondente à escolha do computador
        when (resultado) {
            0 -> binding.idComput.setBackgroundResource(R.drawable.pedra)
            1 -> binding.idComput.setBackgroundResource(R.drawable.papel)
            2 -> binding.idComput.setBackgroundResource(R.drawable.tesoura)
        }

        // Define o resultado do jogo
        val end = when {
            resultado == num -> {
                binding.idResult.setTextColor(Color.GRAY)
                "EMPATE"
            }
            (resultado == 0 && num == 1) || (resultado == 1 && num == 2) || (resultado == 2 && num == 0) -> {
                binding.idResult.setTextColor(Color.BLUE)
                "GANHOU"
            }
            else -> {
                binding.idResult.setTextColor(Color.RED)
                "PERDEU"
            }
        }
        //Fim
        return end
    }
}
