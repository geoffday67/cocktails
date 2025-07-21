package uk.co.sullenart.cocktails.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
fun DetailScreen(
    drink: Drink,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                text = drink.name,
                style = MaterialTheme.typography.headlineMedium,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = drink.imageUrl,
                    contentDescription = drink.name,
                )
            }
            Text(
                text = drink.instructions,
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        DetailScreen(
            drink = Drink(
                id = "blah",
                name = "First drink",
                imageUrl = "https://www.thecocktaildb.com/images/media/drink/qwvwqr1441207763.jpg",
                instructions = "Instructions",
            ),
        )
    }
}