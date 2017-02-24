package wallhow.dreamlo.sdk

import com.google.gson.*
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.HttpHostConnectException
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.DefaultHttpClient
import wallhow.dreamlo.sdk.achievements.Achievements
import wallhow.dreamlo.sdk.leaderboard.Leaderboard
import wallhow.dreamlo.sdk.utils.User
import java.net.URI
import java.util.*

/**
 * Created by wallhow on 23.02.17.
 */

class DreamloSDK(private val publicKey: String,private val privateKey: String) {
    private val httpClient = DefaultHttpClient()
    private val responseHandler = BasicResponseHandler()
    private val httpGet = HttpGet()
    private val gson = Gson()
    private val host = "http://dreamlo.com/lb/"
    val leaderboard = Leaderboard()
    val achievemts = Achievements()

    init {
        dreamloSDK = this
    }

    @Throws(HttpHostConnectException::class)
    private fun execute(url : String, key: String) : String {
        httpGet.uri = URI.create("$host$key/$url")
        return httpClient.execute(httpGet, responseHandler).toString()
    }

    private enum class Type(val url: String) {
        add("add/"),
        delete("delete/"),
        clear("clear/"),

        jsonGet("json-get/")
    }

    companion object instance {
        val KEY_WORD = "Achievements"
        val gson = Gson()
        private lateinit var dreamloSDK: DreamloSDK
        private val httpClient = DefaultHttpClient()
        private val responseHandler = BasicResponseHandler()
        private val httpGet = HttpGet()

        private val host = "http://dreamlo.com/lb/"

        @Throws(HttpHostConnectException::class)
        private fun execute(url : String, key: String) : String {
            httpGet.uri = URI.create("$host$key/$url")
            return httpClient.execute(httpGet, responseHandler).toString()
        }

        fun privateExecute(url : String) : String {
            return execute(url, dreamloSDK.privateKey)
        }
        fun publicExecute(url : String) : String {
            return execute(url, dreamloSDK.publicKey)
        }

    }
}