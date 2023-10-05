package com.example.w2_app.ui.SplashScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.w2_app.R
import com.example.w2_app.ui.Main.MainActivity
import com.example.w2_app.ui.ModelFactory.ThemeModelFactory
import com.example.w2_app.utils.SettingPreferences
import com.example.w2_app.utils.ThemeViewModel

class SplashScreen : AppCompatActivity() {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val countdownTimer = object : CountDownTimer(hit1, hit2) {
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                navigateToMainActivity()
            }
        }
        countdownTimer.start()

        val pref = SettingPreferences.getInstance(application.dataStore)

        val themeViewModel = ViewModelProvider(this, ThemeModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        themeViewModel.getThemeSettings().observe(this) {isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val hit1:Long = 3000
        const val hit2:Long  = 1000
    }

}