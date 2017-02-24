package wallhow.dreamlo.sdk.leaderboard

import wallhow.dreamlo.sdk.DreamloSDK
import wallhow.dreamlo.sdk.utils.User
import java.util.*

/**
 * Created by wallhow on 23.02.17.
 */
class Leaderboard {
    private val mapUsers = ArrayList<Pair<String, User>>()
    private val userNotFound = User().apply { name = User.NOT_FOUND }

    var update = true

    fun setScore(name: String, score: Int) {
        if(name == DreamloSDK.KEY_WORD) {
            throw Exception("Вы используете зарезервированное имя ${DreamloSDK.KEY_WORD}, которое нельзя использовать")
        }
        DreamloSDK.privateExecute("add/$name/$score")
        update = true
    }
    fun setUser(user: User) {
        if(user.name == DreamloSDK.KEY_WORD) {
            throw Exception("Вы используете зарезервированное имя ${DreamloSDK.KEY_WORD}, которое нельзя использовать")
        }
        DreamloSDK.privateExecute("add/${user.name}/${user.score}/${user.seconds}/${user.text}")
        update = true
    }

    fun getUsers() : Array<User>? {
        if(update) {
            updateUserBase()
        }
        return Array(mapUsers.size) { mapUsers[it].second }
    }
    fun getUser(name: String) : User {
        if(update) updateUserBase()
        if(name == DreamloSDK.KEY_WORD) {
            throw Exception("Вы используете зарезервированное имя ${DreamloSDK.KEY_WORD}, которое нельзя использовать")
        }

        return mapUsers.firstOrNull { it.first.contains(name) }?.second ?: userNotFound
    }

    fun getScore(name: String) : Int = getUser(name).score

    private fun updateUserBase() {
        mapUsers.clear()
        val array = DreamloSDK.publicExecute(("pipe-get/")).split("\n").filter { it!="" }
        array.filter { !it.contains(DreamloSDK.KEY_WORD) }.map {
            val usr=User.create(it, "|")
            mapUsers.add(Pair(usr.name,usr))
            usr }
        update = false
    }
}