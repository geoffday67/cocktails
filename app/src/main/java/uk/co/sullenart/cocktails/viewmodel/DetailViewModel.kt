package uk.co.sullenart.cocktails.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uk.co.sullenart.cocktails.network.CocktailService
import uk.co.sullenart.cocktails.model.Drink

class DetailViewModel(
    val drinkId: String,
) : ViewModel() {
    private val drinksService = CocktailService()

    var drink by mutableStateOf(Drink.EMPTY)

    init {
        viewModelScope.launch {
            drink = drinksService.getDrinkForId(drinkId)
        }
    }
}