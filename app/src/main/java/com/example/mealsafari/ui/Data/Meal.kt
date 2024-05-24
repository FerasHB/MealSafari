package syntax.com.playground.data.model.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "meal_information")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val idMeal: Long = 0,

    @Json(name = "strMeal")
    val name: String,

    @Json(name = "strCategory")
    val category: String = "",

    @Json(name = "strArea")
    val area: String = "",

    @Json(name = "strMealThumb")
    val image: String = "",

    @Json(name = "strInstructions")
    val instruction: String = "",

    @Json(name = "strYoutube")
    val video: String = "",

    ) : Serializable
