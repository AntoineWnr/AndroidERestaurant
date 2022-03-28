package fr.isen.wynar.androiderestaurant.apiRestaurant

data class Item (var name_fr : String, var images : List<String>, var prices : List<Prices>, var ingredients: ArrayList<Ingredient>) : java.io.Serializable
