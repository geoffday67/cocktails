package uk.co.sullenart.cocktails.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uk.co.sullenart.cocktails.datasource.drinks.DrinksNetworkSource
import uk.co.sullenart.cocktails.datasource.drinks.DrinksSource
import uk.co.sullenart.cocktails.datasource.ingredients.IngredientsNetworkSource
import uk.co.sullenart.cocktails.datasource.ingredients.IngredientsSource
import uk.co.sullenart.cocktails.network.CocktailService

@Module
@InstallIn(ActivityRetainedComponent::class)
object HiltModule {
    @Provides
    fun providesIngredientsSource(cocktailService: CocktailService): IngredientsSource =
        IngredientsNetworkSource(cocktailService)

    @Provides
    fun providesDrinksSource(cocktailService: CocktailService): DrinksSource =
        DrinksNetworkSource(cocktailService)
}