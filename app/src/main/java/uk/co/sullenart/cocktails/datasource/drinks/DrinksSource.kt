package uk.co.sullenart.cocktails.datasource.drinks

import uk.co.sullenart.cocktails.model.Drink

interface DrinksSource {
    suspend fun getDrinksForIngredient(ingredient: String): List<Drink>
}