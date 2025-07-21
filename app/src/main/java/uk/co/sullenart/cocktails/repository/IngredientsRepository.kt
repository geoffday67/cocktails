package uk.co.sullenart.cocktails.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import uk.co.sullenart.cocktails.datasource.ingredients.IngredientsSource
import uk.co.sullenart.cocktails.model.Ingredient
import javax.inject.Inject

class IngredientsRepository @Inject constructor(
    val dataSource: IngredientsSource,
) {
    private val _ingredientsFlow = MutableSharedFlow<List<Ingredient>>()
    val ingredientsFlow: Flow<List<Ingredient>>
        get() = _ingredientsFlow

    suspend fun refresh() {
        _ingredientsFlow.emit(dataSource.getIngredients())
    }
}