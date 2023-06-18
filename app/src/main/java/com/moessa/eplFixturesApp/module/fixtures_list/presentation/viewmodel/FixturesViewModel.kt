package com.moessa.eplFixturesApp.module.fixtures_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moessa.eplFixturesApp.module.fixtures_list.domain.usecase.GetFixturesListUseCase
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel.FixtureUiModel
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.uimodel.mapper.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(
    private val getFixturesListUseCase: GetFixturesListUseCase
) : ViewModel() {

    private val _fixturesListStateFlow = MutableStateFlow<List<FixtureUiModel>?>(listOf())
    fun fixturesListStateFlow(): StateFlow<List<FixtureUiModel>?> = _fixturesListStateFlow

    private val _errorStateFlow = MutableStateFlow<Throwable?>(null)
    fun errorStateFlow(): StateFlow<Throwable?> = _errorStateFlow

    private val _loadingStateFlow = MutableStateFlow(false)
    fun loadingStateFlow(): StateFlow<Boolean> = _loadingStateFlow

    fun getFixturesList() {

        viewModelScope.launch(Dispatchers.IO) {
            _loadingStateFlow.emit(true)
            getFixturesListUseCase().let {
                _loadingStateFlow.emit(false)
                _fixturesListStateFlow.emit(it?.map { fixtureEntity ->
                    fixtureEntity.toUiModel()
                })
            }
        }
    }
}