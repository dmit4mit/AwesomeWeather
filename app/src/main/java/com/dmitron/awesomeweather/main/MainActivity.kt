package com.dmitron.awesomeweather.main

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dmitron.awesomeweather.R
import com.dmitron.awesomeweather.databinding.ActivityMainBinding
import com.dmitron.awesomeweather.utils.enableFullscreen
import com.dmitron.awesomeweather.utils.executeAfter
import com.dmitron.awesomeweather.utils.inputMethodManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainUiController {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executeAfter {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }
        window.enableFullscreen()
    }

    override fun hideKeyboard() {
        inputMethodManager().hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun showKeyboard() {
        inputMethodManager().toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }
}