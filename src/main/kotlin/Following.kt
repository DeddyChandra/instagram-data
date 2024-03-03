import com.fasterxml.jackson.annotation.JsonProperty

data class Following(
    @JsonProperty("relationships_following")
    val relationshipsFollowing: List<User>
)
