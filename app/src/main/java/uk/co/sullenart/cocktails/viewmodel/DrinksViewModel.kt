package uk.co.sullenart.cocktails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.co.sullenart.cocktails.model.Drink
import uk.co.sullenart.cocktails.repository.DrinksRepository
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    val drinksRepository: DrinksRepository,
    ) : ViewModel() {
    val drinksList = mutableStateListOf<Drink>()
    var ingredientName by mutableStateOf("")

    init {
        viewModelScope.launch {
            drinksRepository.drinksFlow.collect { drinks ->
                drinksList.clear()
                drinksList.addAll(drinks)
            }
        }
    }

    fun updateIngredientName(ingredientName: String) {
        this.ingredientName = ingredientName
        viewModelScope.launch {
            drinksRepository.getDrinksForIngredient(ingredientName)
        }
    }

    fun handleDrinkSelected(drink: Drink) {
//        viewModelScope.launch {
//            navController.navigate(DetailRoute(drink.id))
//        }
    }
}