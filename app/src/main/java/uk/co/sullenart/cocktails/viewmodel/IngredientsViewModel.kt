package uk.co.sullenart.cocktails.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uk.co.sullenart.cocktails.DrinksRoute
import uk.co.sullenart.cocktails.model.Ingredient
import uk.co.sullenart.cocktails.repository.IngredientsRepository
import uk.co.sullenart.cocktails.ui.CocktailsState
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    val ingredientsRepository: IngredientsRepository,
) : ViewModel() {
    private val _stateFlow = MutableStateFlow<CocktailsState>(CocktailsState.Empty())
    val stateFlow: Flow<CocktailsState>
        get() = _stateFlow

    init {
        viewModelScope.launch {
            ingredientsRepository.ingredientsFlow.collect {
                val newState = CocktailsState.Ingredients(it)
                _stateFlow.emit(newState)
            }
        }

        viewModelScope.launch {
            _stateFlow.emit(CocktailsState.Loading())
            ingredientsRepository.refresh()
        }
    }

    fun handleIngredientSelected(ingredient: Ingredient, navController: NavController) {
        viewModelScope.launch {
            navController.navigate(DrinksRoute(ingredient.name))
        }
    }
}