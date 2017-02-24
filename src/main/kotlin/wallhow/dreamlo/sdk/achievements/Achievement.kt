package wallhow.dreamlo.sdk.achievements

/**
 * Created by wallhow on 24.02.17.
 */
class Achievement {
    var key: String = ""
    var isOpen: Boolean = false

    companion object {
        val NOT_FOUND = "AchievementNotFound"
    }

    override fun toString(): String {
        return StringBuilder().append("[ Achievement:$key ]\n").append("is open = ").append(isOpen)
                .append("\n").toString()
    }
}