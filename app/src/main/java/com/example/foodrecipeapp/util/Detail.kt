package com.example.foodrecipeapp.util

import com.google.gson.annotations.SerializedName

data class Detail(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int?,
    @SerializedName("analyzedInstructions")
    val analyzedInstructions: List<Any>?,
    @SerializedName("cheap")
    val cheap: Boolean?,
    @SerializedName("cookingMinutes")
    val cookingMinutes: Int?,
    @SerializedName("creditsText")
    val creditsText: String?,
    @SerializedName("cuisines")
    val cuisines: List<Any>?,
    @SerializedName("dairyFree")
    val dairyFree: Boolean?,
    @SerializedName("diets")
    val diets: List<Any>?,
    @SerializedName("dishTypes")
    val dishTypes: List<String>?,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>?,
    @SerializedName("gaps")
    val gaps: String?,
    @SerializedName("glutenFree")
    val glutenFree: Boolean?,
    @SerializedName("healthScore")
    val healthScore: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("imageType")
    val imageType: String?,
    @SerializedName("instructions")
    val instructions: String?,
    @SerializedName("license")
    val license: String?,
    @SerializedName("lowFodmap")
    val lowFodmap: Boolean?,
    @SerializedName("nutrition")
    val nutrition: Nutrition?,
    @SerializedName("occasions")
    val occasions: List<Any>?,
    @SerializedName("originalId")
    val originalId: Any?,
    @SerializedName("preparationMinutes")
    val preparationMinutes: Int?,
    @SerializedName("pricePerServing")
    val pricePerServing: Double?,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int?,
    @SerializedName("servings")
    val servings: Int?,
    @SerializedName("sourceName")
    val sourceName: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String?,
    @SerializedName("spoonacularSourceUrl")
    val spoonacularSourceUrl: String?,
    @SerializedName("summary")
    val summary: String?,
    @SerializedName("sustainable")
    val sustainable: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vegan")
    val vegan: Boolean?,
    @SerializedName("vegetarian")
    val vegetarian: Boolean?,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean?,
    @SerializedName("veryPopular")
    val veryPopular: Boolean?,
    @SerializedName("weightWatcherSmartPoints")
    val weightWatcherSmartPoints: Int?,
    @SerializedName("winePairing")
    val winePairing: WinePairing?
)
data class CaloricBreakdown(
    @SerializedName("percentCarbs")
    val percentCarbs: Double?,
    @SerializedName("percentFat")
    val percentFat: Double?,
    @SerializedName("percentProtein")
    val percentProtein: Double?
)
data class ExtendedIngredient(
    @SerializedName("aisle")
    val aisle: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("consistency")
    val consistency: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("measures")
    val measures: Measures?,
    @SerializedName("meta")
    val meta: List<String?>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nameClean")
    val nameClean: String?,
    @SerializedName("original")
    val original: String?,
    @SerializedName("originalName")
    val originalName: String?,
    @SerializedName("unit")
    val unit: String?
)
data class Flavonoid(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("unit")
    val unit: String?
)
data class Ingredient(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nutrients")
    val nutrients: List<NutrientX>?,
    @SerializedName("unit")
    val unit: String?
)
data class Measures(
    @SerializedName("metric")
    val metric: Metric?,
    @SerializedName("us")
    val us: Us?
)
data class Metric(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unitLong")
    val unitLong: String?,
    @SerializedName("unitShort")
    val unitShort: String?
)
data class NutrientX(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double?,
    @SerializedName("unit")
    val unit: String?
)
data class Nutrition(
    @SerializedName("caloricBreakdown")
    val caloricBreakdown: CaloricBreakdown?,
    @SerializedName("flavonoids")
    val flavonoids: List<Flavonoid>?,
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>?,
    @SerializedName("nutrients")
    val nutrients: List<NutrientX>?,
    @SerializedName("properties")
    val properties: List<Property>?,
    @SerializedName("weightPerServing")
    val weightPerServing: WeightPerServing?
)
data class Property(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("unit")
    val unit: String?
)
data class Us(
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("unitLong")
    val unitLong: String?,
    @SerializedName("unitShort")
    val unitShort: String?
)
data class WeightPerServing(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("unit")
    val unit: String?
)
data class WinePairing(
    @SerializedName("pairedWines")
    val pairedWines: List<Any?>?,
    @SerializedName("pairingText")
    val pairingText: String?,
    @SerializedName("productMatches")
    val productMatches: List<Any?>?
)