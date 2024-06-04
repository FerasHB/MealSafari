package syntax.com.playground.data.model.meal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

/**
 * Datenklasse, die eine Mahlzeit repräsentiert.
 * @param idMeal Die eindeutige ID der Mahlzeit.
 * @param name Der Name der Mahlzeit.
 * @param category Die Kategorie, zu der die Mahlzeit gehört.
 * @param area Der Bereich, aus dem die Mahlzeit stammt.
 * @param image Die URL des Bildes der Mahlzeit.
 * @param instruction Die Anweisungen zur Zubereitung der Mahlzeit.
 * @param video Die URL des Videos, das die Zubereitung der Mahlzeit zeigt.
 */
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
