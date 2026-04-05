import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    val title: String,
    @JsonProperty("media_list_data")
    val mediaListData: List<Any> = emptyList(),
    @JsonProperty("string_list_data")
    val stringListData: List<StringListData>
){
    data class StringListData(
        val href: String,
        val value: String?,
        val timestamp: Long
    ) {
        fun extractUsernameFromHref(): String {
            // Remove the prefix from href and return the rest
            val prefix = "https://www.instagram.com/_u/"
            return if (href.startsWith(prefix)) {
                href.removePrefix(prefix)
            } else {
                href
            }
        }
    }
}
