package com.ofekpintok.janitor.features.janitor.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ofekpintok.janitor.features.janitor.domain.CalculateTripsUseCase
import com.ofekpintok.janitor.features.janitor.domain.model.Bag
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class JanitorViewModel(
    savedStateHandle: SavedStateHandle,
    private val calculateTripsUseCase: CalculateTripsUseCase
): ViewModel() {

    companion object {
        const val MIN_WEIGHT = 1.01
        const val MAX_WEIGHT = 3.00
    }

    private val _bagsState: MutableStateFlow<List<Bag>> = savedStateHandle.getMutableStateFlow("bags_key", emptyList())

    val bagsState =
        _bagsState
            .map { it.toImmutableList() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                persistentListOf()
            )

    val tripsState =
        _bagsState
            .map { calculateTripsUseCase(it).toImmutableList() }
            .flowOn(Dispatchers.Default)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                persistentListOf()
            )

    fun addBag(weight: Double) {
        _bagsState.update {
            it + Bag(index = it.size, weight = weight)
        }
    }

    fun resetJanitor() {
        _bagsState.update {
            persistentListOf()
        }
    }

    fun isOutOfRange(weightInput: String): Boolean {
        val weight = weightInput.toDoubleOrNull() ?: return false
        return weight !in MIN_WEIGHT..MAX_WEIGHT
    }

}