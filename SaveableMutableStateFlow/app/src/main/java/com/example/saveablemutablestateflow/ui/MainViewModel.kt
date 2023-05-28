package com.example.saveablemutablestateflow.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.saveablemutablestateflow.getSaveableMutableStateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _textStateFlow = MutableStateFlow("")
    val textStateFlow = _textStateFlow.asStateFlow()

    private val _textSaveableStateFlow =
        savedStateHandle.getSaveableMutableStateFlow("userInput", "")
    val textSaveableStateFlow = _textSaveableStateFlow.asStateFlow()

    private val _numberStateFlow = MutableStateFlow(0)
    val numberStateFlow = _numberStateFlow.asStateFlow()

    private val _numberSaveableStateFlow =
        savedStateHandle.getSaveableMutableStateFlow("number", 0)
    val numberSaveableStateFlow = _numberSaveableStateFlow.asStateFlow()

    private val _scrollState =
        savedStateHandle.getSaveableMutableStateFlow("scrollState", Pair(0, 0))
    val scrollState = _scrollState.asStateFlow()

    fun plus() {
        _numberStateFlow.value = numberStateFlow.value + 1
        _numberSaveableStateFlow.value = numberSaveableStateFlow.value + 1
    }

    fun minus() {
        _numberStateFlow.value = numberStateFlow.value - 1
        _numberSaveableStateFlow.value = numberSaveableStateFlow.value - 1
    }

    fun setStateFlowText(text: String) {
        _textStateFlow.value = text
    }

    fun setSaveableStateFlowText(text: String) {
        _textSaveableStateFlow.value = text
    }

    fun setScrollState(scrollX: Int, scrollY: Int) {
        _scrollState.value = Pair(scrollX, scrollY)
    }
}
