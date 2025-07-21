package uk.co.sullenart.cocktails.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uk.co.sullenart.cocktails.model.Drink

@Composable
fun DrinksScreen(
    ingredientName: String,
    drinks: List<Drink>,
    onDrinkSelected: (Drink) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = ingredientName,
            style = MaterialTheme.typography.headlineMedium,
        )
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(drinks) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .clickable { onDrinkSelected(it) },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = it.name,
                    )
                    AsyncImage(
                        modifier = Modifier
                            .heightIn(max = 60.dp),
                        model = it.imageUrl,
                        contentDescription = it.name,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DrinksPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        DrinksScreen(
            ingredientName = "Preview",
            drinks = listOf(
                Drink(
                    id = "first",
                    name = "First drink",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/qwvwqr1441207763.jpg",
                    instructions = "Instructions",
                ),
                Drink(
                    id = "second",
                    name = "Second drink",
                    imageUrl = "https://www.thecocktaildb.com/images/media/drink/qwvwqr1441207763.jpg",
                    instructions = "Instructions",
                ),
            ),
            onDrinkSelected = {},
        )
    }
}