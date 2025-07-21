package uk.co.sullenart.cocktails.network

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.sullenart.cocktails.model.Drink
import uk.co.sullenart.cocktails.model.Ingredient
import javax.inject.Inject

@Serializable
data class IngredientsResponse(
    val drinks: List<IngredientResponse>
) {
    @Serializable
    data class IngredientResponse(
        val strIngredient1: String,
    )
}

@Serializable
data class DrinksResponse(
    val drinks: List<DrinkResponse>
) {
    @Serializable
    data class DrinkResponse(
        val idDrink: String? = null,
        val strDrink: String? = null,
        val strDrinkThumb: String? = null,
        val strInstructions: String? = null,
    )
}

class CocktailService @Inject constructor() {
    private interface DrinksApi {
        @GET("list.php?i=list")
        suspend fun getIngredientList(): IngredientsResponse

        @GET("filter.php")
        suspend fun getDrinksForIngredient(@Query("i") ingredient: String): DrinksResponse

        @GET("lookup.php")
        suspend fun getDrinkDetails(@Query("i") id: String): DrinksResponse
    }

    private val drinksImpl: DrinksApi

    init {
        val json = Json {
            ignoreUnknownKeys = true
        }

        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(
                    json.asConverterFactory(
                        "application/json; charset=UTF8".toMediaType()
                    )
                )
                .build()

        drinksImpl = retrofit.create(DrinksApi::class.java)
    }

    suspend fun listIngredients(): List<Ingredient> {
        return drinksImpl.getIngredientList().drinks.map {
            Ingredient(
                name = it.strIngredient1,
            )
        }
    }

    suspend fun listDrinksForIngredient(ingredient: String): List<Drink> {
        return drinksImpl.getDrinksForIngredient(ingredient).drinks.map {
            Drink(
                id = it.idDrink.orEmpty(),
                name = it.strDrink.orEmpty(),
                imageUrl = it.strDrinkThumb.orEmpty(),
                instructions = "",
            )
        }
    }

    suspend fun getDrinkForId(id: String): Drink {
        val response = drinksImpl.getDrinkDetails(id).drinks.firstOrNull()
        return Drink(
            id = response?.idDrink.orEmpty(),
            name = response?.strDrink.orEmpty(),
            imageUrl = response?.strDrinkThumb.orEmpty(),
            instructions = response?.strInstructions.orEmpty(),
        )
    }
}