package com.example.myapplication.mainComponets

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    private var aktualnaWartosc: Int = 0

    // DODANE NOWE ZMIENNE STANU DLA LOGIKI UI
    private var czyUjemneDozwolone: Boolean = false
    private var czyLiczenieParami: Boolean = false

    // Właściwość obliczana do określenia kroku (1 lub 2)
    private val krokZmiany: Int
        get() = if (czyLiczenieParami) 2 else 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Użycie binding.root dla Window Insets (bezpieczniejsza korekta)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Wywołanie początkowe
        aktualizujWyswietlacz()

        // --- OBSŁUGA PRZYCISKÓW LICZENIA (Z KROKIEM I SPRAWDZENIEM UJEMNYCH) ---

        binding.buttonPlus.setOnClickListener {
            aktualnaWartosc += krokZmiany // Użycie kroku zmiany
            aktualizujWyswietlacz()
        }

        binding.buttonMinus.setOnClickListener {
            aktualnaWartosc -= krokZmiany // Użycie kroku zmiany

            // Logika: Jeśli ujemne są ZABLOKOWANE i wynik jest ujemny, ustaw na 0
            if (!czyUjemneDozwolone && aktualnaWartosc < 0) {
                aktualnaWartosc = 0
            }
            aktualizujWyswietlacz()
        }

        binding.buttonReset.setOnClickListener {
            aktualnaWartosc = 0
            aktualizujWyswietlacz()
        }

        // --- OBSŁUGA NOWYCH WIDOKÓW UI ---

        // Checkbox: allowNegativeNumberCheckbox (Ustawia flagę czyUjemneDozwolone)
        binding.allowNegativeNumberCheckbox.setOnCheckedChangeListener { _, isChecked ->
            czyUjemneDozwolone = isChecked
            // Jeśli opcja zostaje wyłączona, a licznik jest ujemny, resetujemy do 0.
            if (!isChecked && aktualnaWartosc < 0) {
                aktualnaWartosc = 0
                aktualizujWyswietlacz()
                Toast.makeText(this, "Licznik zresetowany do 0 (ujemne wyłączone).", Toast.LENGTH_SHORT).show()
            }
        }

        // Switch: countByPairSwitch (Ustawia flagę czyLiczenieParami)
        binding.countByPairSwitch.setOnCheckedChangeListener { _, isChecked ->
            czyLiczenieParami = isChecked
            val komunikat = if (isChecked) "Liczenie co 2 włączone." else "Liczenie co 1 włączone."
            Toast.makeText(this, komunikat, Toast.LENGTH_SHORT).show()
        }

        // Przycisk i Pole Tekstowe: Ustawianie wartości domyślnej
        binding.setYourNumberButton.setOnClickListener {
            // Bezpieczne pobranie tekstu z pola EditText
            val wpisanyTekst = binding.enterDefaultValueEt.text.toString()

            // Próba konwersji tekstu na liczbę całkowitą
            val nowaWartosc = wpisanyTekst.toIntOrNull()

            if (nowaWartosc != null) {
                aktualnaWartosc = nowaWartosc
                aktualizujWyswietlacz()
                // Opcjonalne: wyczyść pole wprowadzania
                binding.enterDefaultValueEt.text.clear()
            } else {
                Toast.makeText(this, "Błąd: Wpisz poprawną liczbę!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun aktualizujWyswietlacz() {
        // Zaktualizuj TextView licznika
        binding.viewCounter.text = aktualnaWartosc.toString()
    }
}