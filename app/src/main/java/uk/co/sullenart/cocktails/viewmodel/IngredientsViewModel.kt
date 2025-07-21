package uk.co.sullenart.cocktails.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.sullenart.cocktails.DrinksRoute
import uk.co.sullenart.cocktails.model.Ingredient
import uk.co.sullenart.cocktails.repository.IngredientsRepository
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    val ingredientsRepository: IngredientsRepository,
) : ViewModel() {
    val ingredientsList = mutableStateListOf<Ingredient>()

    init {
        viewModelScope.launch {
            ingredientsRepository.ingredientsFlow.collect { ingredients ->
                ingredientsList.clear()
                ingredientsList.addAll(ingredients)
            }
        }

        viewModelScope.launch {
            ingredientsRepository.refresh()
        }
    }

    fun handleIngredientSelected(ingredient: Ingredient, navController: NavController) {
        viewModelScope.launch {
            navController.navigate(DrinksRoute(ingredient.name))
        }
    }
}