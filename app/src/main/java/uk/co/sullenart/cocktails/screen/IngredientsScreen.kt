package uk.co.sullenart.cocktails.screen

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.co.sullenart.cocktails.ui.CocktailsIntent
import uk.co.sullenart.cocktails.ui.CocktailsState

@Composable
fun IngredientsScreen(
    cocktailsState: CocktailsState,
    onIntent: (CocktailsIntent) -> Unit,
) {
    when (cocktailsState) {
        is CocktailsState.Loading -> ShowLoading()
        is CocktailsState.Ingredients -> ShowIngredients(cocktailsState, onIntent)
        else -> {}
    }
}

@Composable
private fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShowIngredients(
    state: CocktailsState.Ingredients,
    onIntent: (CocktailsIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Choose an ingredient",
            style = MaterialTheme.typography.headlineMedium,
        )
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 16.dp),
        ) {
            items(state.ingredients) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = { onIntent(CocktailsIntent.ChooseIngredient(it)) },
                            onLongClick = { onIntent(CocktailsIntent.IngredientInfo(it)) },
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(6.dp),
                        text = it.name,
                    )
                }
            }
        }
    }

    state.info?.let { info ->
        AlertDialog(
            onDismissRequest = { onIntent(CocktailsIntent.DismissInfo) },
            title = { Text("Ingredient") },
            text = { Text(info) },
            confirmButton = {
                TextButton(onClick = { onIntent(CocktailsIntent.DismissInfo) }) {
                    Text("OK")
                }
            },
        )
    }
}
