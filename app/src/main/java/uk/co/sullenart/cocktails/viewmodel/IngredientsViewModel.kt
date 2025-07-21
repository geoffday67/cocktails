package uk.co.sullenart.cocktails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uk.co.sullenart.cocktails.DrinksRoute
import uk.co.sullenart.cocktails.repository.IngredientsRepository
import uk.co.sullenart.cocktails.ui.CocktailsIntent
import uk.co.sullenart.cocktails.ui.CocktailsState
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    val ingredientsRepository: IngredientsRepository,
) : ViewModel() {
    private var state: CocktailsState = CocktailsState.Empty()
        set(value) {
            field = value
            viewModelScope.launch {
                _stateFlow.emit(value)
            }
        }
    private val _stateFlow = MutableStateFlow(state)
    val stateFlow: Flow<CocktailsState>
        get() = _stateFlow

    init {
        viewModelScope.launch {
            ingredientsRepository.ingredientsFlow.collect {
                state = CocktailsState.Ingredients(it)
            }
        }

        viewModelScope.launch {
            state = CocktailsState.Loading()
            ingredientsRepository.refresh()
        }
    }

    fun handleIntent(intent: CocktailsIntent, navController: NavController) {
        when (intent) {
            is CocktailsIntent.ChooseIngredient -> {
                viewModelScope.launch {
                    navController.navigate(DrinksRoute(intent.ingredient.name))
                }
            }
            is CocktailsIntent.IngredientInfo -> (state as? CocktailsState.Ingredients)?.let{
                state = it.copy(info = "${intent.ingredient.name} is great!")
            }
            is CocktailsIntent.DismissInfo -> (state as? CocktailsState.Ingredients)?.let{
                state = it.copy(info = null)
            }
        }
    }
}