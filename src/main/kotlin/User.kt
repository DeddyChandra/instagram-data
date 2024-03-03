import com.fasterxml.jackson.annotation.JsonProperty
import java.sql.Timestamp

data class User(
    val title: String,
    @JsonProperty("media_list_data")
    val mediaListData: List<Any>,
    @JsonProperty("string_list_data")
    val stringListData: List<StringListData>
){
    data class StringListData(
        val href: String,
        val value: String,
        val timestamp: Long
    )
}
