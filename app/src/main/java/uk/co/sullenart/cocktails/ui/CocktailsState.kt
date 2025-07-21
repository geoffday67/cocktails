package uk.co.sullenart.cocktails.ui

import uk.co.sullenart.cocktails.model.Ingredient

sealed class CocktailsState {
    class Empty: CocktailsState()

    class Loading: CocktailsState()

    data class Ingredients(
        val ingredients: List<Ingredient>,
        val info: String? = null,
    ): CocktailsState()
}

