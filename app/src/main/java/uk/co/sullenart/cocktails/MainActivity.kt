package uk.co.sullenart.cocktails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import uk.co.sullenart.cocktails.screen.DetailScreen
import uk.co.sullenart.cocktails.screen.DrinksScreen
import uk.co.sullenart.cocktails.screen.IngredientsScreen
import uk.co.sullenart.cocktails.ui.CocktailsState
import uk.co.sullenart.cocktails.ui.theme.CocktailsTheme
import uk.co.sullenart.cocktails.viewmodel.DetailViewModel
import uk.co.sullenart.cocktails.viewmodel.DrinksViewModel
import uk.co.sullenart.cocktails.viewmodel.IngredientsViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()

            CocktailsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = IngredientsRoute,
                        ) {
                            composable<IngredientsRoute> {
                                val ingredientsViewModel: IngredientsViewModel by viewModels()
                                IngredientsScreen(
                                    cocktailsState = ingredientsViewModel.stateFlow.collectAsState(CocktailsState.Empty()).value,
                                    onIngredientSelected = {
                                        ingredientsViewModel.handleIngredientSelected(
                                            ingredient = it,
                                            navController = navController,
                                        )
                                    },
                                )
                            }

                            composable<DrinksRoute> {
                                val route: DrinksRoute = it.toRoute()
                                val drinksViewModel: DrinksViewModel by viewModels()
                                drinksViewModel.updateIngredientName(route.ingredientName)
                                DrinksScreen(
                                    ingredientName = drinksViewModel.ingredientName,
                                    drinks = drinksViewModel.drinksList,
                                    onDrinkSelected = { drinksViewModel.handleDrinkSelected(it) },
                                )
                            }

                            composable<DetailRoute> {
                                val route: DetailRoute = it.toRoute()
                                val detailsViewModel = remember {
                                    DetailViewModel(
                                        drinkId = route.drinkId,
                                    )
                                }
                                DetailScreen(
                                    drink = detailsViewModel.drink,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object IngredientsRoute

@Serializable
data class DrinksRoute(
    val ingredientName: String,
)

@Serializable
data class DetailRoute(
    val drinkId: String,
)
