package uk.co.sullenart.cocktails.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.co.sullenart.cocktails.model.Ingredient

@Composable
fun IngredientsScreen(
    ingredients: List<Ingredient>,
    onIngredientSelected: (Ingredient) -> Unit,
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
            items(ingredients) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onIngredientSelected(it) },
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        IngredientsScreen(
            ingredients = listOf(
                Ingredient("First ingredient"),
                Ingredient("Second ingredient"),
            ),
            onIngredientSelected = {},
        )
    }
}