package uk.co.sullenart.cocktails.datasource.ingredients

import uk.co.sullenart.cocktails.model.Ingredient
import uk.co.sullenart.cocktails.network.CocktailService
import javax.inject.Inject

class IngredientsNetworkSource @Inject constructor(
    val cocktailService: CocktailService,
) : IngredientsSource {
    override suspend fun getIngredients(): List<Ingredient> {
        return cocktailService.listIngredients()
    }
}