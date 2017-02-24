package wallhow.dreamlo.sdk.achievements

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import wallhow.dreamlo.sdk.DreamloSDK
import wallhow.dreamlo.sdk.utils.User
import java.util.*

/**
 * Created by wallhow on 24.02.17.
 */
class Achievements {
    @SerializedName("achievements")
    private var achives = ArrayList<Achievement>()
    private var update = true
    private var list : MutableCollection<Achievement>? = null
    private var notFound = Achievement().apply { key = Achievement.NOT_FOUND }
    private val type = object : TypeToken<ArrayList<Achievement>>(){}.type

    fun get(key: String) : Achievement {
        if(update) updateBase()
        var achive = notFound
        achives.filter { it.key == key }.forEach { achive = it }
        return achive
    }

    fun set(key: String, isOpen: Boolean) {
        var achive = notFound
        achives.filter { it.key == key }.forEach { achive = it }
        if(achive.key == notFound.key)
            achives.add(Achievement().apply { this.key = key; this.isOpen = isOpen})
        else
            achive.apply { this.isOpen = isOpen}
        write()
    }
    fun list() : MutableCollection<Achievement>? {
        if(update) {
            updateBase()
            list = achives
        }
        return list
    }

    private fun write() {
        update = true
        DreamloSDK.privateExecute("add/${DreamloSDK.KEY_WORD}/0/0/${
        Base64.getEncoder().encode((DreamloSDK.gson.toJson(achives)).toByteArray()).toString(Charsets.UTF_8)
        }")
    }

    private fun updateBase() {
        update = false
        val userPipe = DreamloSDK.publicExecute("pipe-get/${DreamloSDK.KEY_WORD}")
        val encodeJson = User.create(userPipe,"|").text.toByteArray()
        achives = DreamloSDK.gson.fromJson(
                Base64.getDecoder().decode(encodeJson).toString(Charsets.UTF_8), type)
    }
}

