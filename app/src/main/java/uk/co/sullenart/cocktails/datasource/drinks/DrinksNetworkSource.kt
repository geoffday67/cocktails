package uk.co.sullenart.cocktails.datasource.drinks

import uk.co.sullenart.cocktails.model.Drink
import uk.co.sullenart.cocktails.model.Ingredient
import uk.co.sullenart.cocktails.network.CocktailService
import javax.inject.Inject

class DrinksNetworkSource @Inject constructor(
    val cocktailService: CocktailService,
) : DrinksSource {
    override suspend fun getDrinksForIngredient(ingredient: String): List<Drink> {
        return cocktailService.listDrinksForIngredient(ingredient)
    }
}