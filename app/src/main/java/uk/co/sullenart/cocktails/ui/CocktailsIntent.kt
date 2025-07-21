package uk.co.sullenart.cocktails.ui

import uk.co.sullenart.cocktails.model.Ingredient

sealed class CocktailsIntent {

    data class ChooseIngredient(
        val ingredient: Ingredient,
    ): CocktailsIntent()

    data class IngredientInfo(
        val ingredient: Ingredient,
    ): CocktailsIntent()

    object DismissInfo: CocktailsIntent()
}