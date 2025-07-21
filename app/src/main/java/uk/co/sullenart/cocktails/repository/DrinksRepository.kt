package uk.co.sullenart.cocktails.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uk.co.sullenart.cocktails.datasource.drinks.DrinksSource
import uk.co.sullenart.cocktails.model.Drink
import javax.inject.Inject

class DrinksRepository @Inject constructor(
    val dataSource: DrinksSource,
) {
    private val _drinksFlow = MutableSharedFlow<List<Drink>>()
    val drinksFlow: Flow<List<Drink>>
        get() = _drinksFlow

    suspend fun getDrinksForIngredient(ingredient: String) {
        _drinksFlow.emit(dataSource.getDrinksForIngredient(ingredient))
    }
}