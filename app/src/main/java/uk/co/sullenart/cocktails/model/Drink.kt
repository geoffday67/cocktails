package uk.co.sullenart.cocktails.model

data class Drink(
    val id: String,
    val name: String,
    val imageUrl: String,
    val instructions: String,
) {
    companion object {
        val EMPTY = Drink(
            id = "",
            name = "",
            imageUrl = "",
            instructions = "",
        )
    }
}