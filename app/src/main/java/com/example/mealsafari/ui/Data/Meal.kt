package syntax.com.playground.data.model.meal

import com.squareup.moshi.Json

data class Meal(
    val idMeal: String,

    @Json(name = "strMeal")
    val name: String,

    @Json(name = "strCategory")
    val category: String = "",

    @Json(name ="strArea")
    val area: String = "",

    @Json(name = "strMealThumb")
    val image: String
)
