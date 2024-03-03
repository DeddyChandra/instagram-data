import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File

fun main(){
    val following = following()
    val followers = followers()
    val getNonFollowers = getNonFollowers(following, followers)
    getNonFollowers.map {
        println(it.stringListData.first().value)
        println(it.stringListData.first().href)
    }
}

fun readData(path: String): String{
    val file = File(path)
    return try {
        if (file.exists()) {
            file.readText()
        } else {
            error("File not found: $path")
        }
    } catch (e: Exception){
        error("Failed to read cookie $e")
    }
}

fun following(): List<User> {
    val data = readData(FOLLOWING_PATH)
    return jacksonObjectMapper().readValue(data, Following::class.java).relationshipsFollowing
}

fun followers(): List<User> {
    val data = readData(FOLLOWER_PATH)
    return jacksonObjectMapper().readValue(data, object : TypeReference<List<User>>() {})
}

fun getNonFollowers(followings: List<User>, followers: List<User>): List<User>{
    return followings.filter{following ->
        followers.none{follower ->
            follower.stringListData.first().value == following.stringListData.first().value
        }
    }
}