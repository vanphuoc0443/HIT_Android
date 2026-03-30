package com.example.calculator

import android.content.Context
import android.content.SharedPreferences

object ThemeHelper {

    private const val PREFS_NAME = "calculator_prefs"
    private const val DEFAULT_THEME = "Light"

    // Theme Keys
    const val THEME_LIGHT = "Light"
    const val THEME_AMBER = "Amber"
    const val THEME_BLUEGRAY = "BlueGray"
    const val THEME_CYAN = "Cyan"
    const val THEME_GREEN = "Green"
    const val THEME_DARK = "Dark"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun saveTheme(context: Context, themeKey: String) {
        val prefs = getPrefs(context)
        prefs.edit().putString("selected_theme", themeKey).apply()
    }

    fun getThemeKey(context: Context): String {
        return getPrefs(context).getString("selected_theme", DEFAULT_THEME) ?: DEFAULT_THEME
    }

    fun getThemeResId(themeKey: String): Int {
        return when (themeKey) {
            THEME_LIGHT -> R.style.Theme_Calculator_Light
            THEME_AMBER -> R.style.Theme_Calculator_Amber
            THEME_BLUEGRAY -> R.style.Theme_Calculator_BlueGray
            THEME_CYAN -> R.style.Theme_Calculator_Cyan
            THEME_GREEN -> R.style.Theme_Calculator_Green
            THEME_DARK -> R.style.Theme_Calculator_Dark
            else -> R.style.Theme_Calculator_Light
        }
    }
}
