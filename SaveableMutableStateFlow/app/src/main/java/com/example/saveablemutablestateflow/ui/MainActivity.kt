package com.example.saveablemutablestateflow.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.saveablemutablestateflow.R
import com.example.saveablemutablestateflow.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var text = ""
    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
    }

    private fun initViews() {
        binding.etNormal.setText(text)
        binding.tvNormal.text = getString(R.string.normal_number, number)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // set scroll state
                launch {
                    viewModel.scrollState.collect { (x, y) ->
                        binding.svMain.scrollTo(x, y)
                    }
                }

                // set edit text
                launch {
                    viewModel.textStateFlow.collect {
                        binding.etStateFlow.setText(it)
                        binding.etStateFlow.setSelection(it.length)
                    }
                }
                launch {
                    viewModel.textSaveableStateFlow.collect {
                        binding.etSaveableStateFlow.setText(it)
                        binding.etSaveableStateFlow.setSelection(it.length)
                    }
                }
                // set text view
                launch {
                    viewModel.numberStateFlow.collect {
                        binding.tvStateFlow.text = getString(R.string.state_flow_number, it)
                    }
                }
                launch {
                    viewModel.numberSaveableStateFlow.collect {
                        binding.tvSaveableStateFlow.text =
                            getString(R.string.saveable_state_flow_number, it)
                    }
                }
            }
        }
    }

    private fun initListeners() = with(binding) {
        btnUp.setOnClickListener { plus() }
        btnDown.setOnClickListener { minus() }
        etNormal.doAfterTextChanged {
            text = it.toString()
        }
        etStateFlow.doAfterTextChanged {
            viewModel.setStateFlowText(it.toString())
        }
        etSaveableStateFlow.doAfterTextChanged {
            viewModel.setSaveableStateFlowText(it.toString())
        }
        svMain.setOnScrollChangeListener { _, scrollX, scrollY, _, _ ->
            viewModel.setScrollState(scrollX, scrollY)
        }
    }

    private fun plus() {
        number++
        binding.tvNormal.text = getString(R.string.normal_number, number)
        viewModel.plus()
    }

    private fun minus() {
        number--
        binding.tvNormal.text = getString(R.string.normal_number, number)
        viewModel.minus()
    }
}
