package uk.co.sullenart.cocktails.datasource.ingredients

import uk.co.sullenart.cocktails.model.Ingredient
import javax.inject.Inject

class OneIngredient @Inject constructor(): IngredientsSource {
    override suspend fun getIngredients(): List<Ingredient> {
        return listOf(Ingredient("Stuff"))
    }
}