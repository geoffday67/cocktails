package uk.co.sullenart.cocktails.datasource.ingredients

import uk.co.sullenart.cocktails.model.Ingredient

interface IngredientsSource {
    suspend fun getIngredients(): List<Ingredient>
}