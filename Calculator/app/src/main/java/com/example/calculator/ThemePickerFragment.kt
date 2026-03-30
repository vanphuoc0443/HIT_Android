package com.example.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ThemePickerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_theme_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<LinearLayout>(R.id.btnThemeLight).setOnClickListener {
            setTheme(ThemeHelper.THEME_LIGHT)
        }
        view.findViewById<LinearLayout>(R.id.btnThemeAmber).setOnClickListener {
            setTheme(ThemeHelper.THEME_AMBER)
        }
        view.findViewById<LinearLayout>(R.id.btnThemeBlueGray).setOnClickListener {
            setTheme(ThemeHelper.THEME_BLUEGRAY)
        }
        view.findViewById<LinearLayout>(R.id.btnThemeCyan).setOnClickListener {
            setTheme(ThemeHelper.THEME_CYAN)
        }
        view.findViewById<LinearLayout>(R.id.btnThemeGreen).setOnClickListener {
            setTheme(ThemeHelper.THEME_GREEN)
        }
        view.findViewById<LinearLayout>(R.id.btnThemeGray).setOnClickListener {
            setTheme(ThemeHelper.THEME_DARK)
        }
    }

    private fun setTheme(themeKey: String) {
        val currentContext = requireContext()
        if (ThemeHelper.getThemeKey(currentContext) != themeKey) {
            ThemeHelper.saveTheme(currentContext, themeKey)
            // Trigger an activity recreate to apply theme
            requireActivity().recreate()
        }
        dismiss()
    }
}
