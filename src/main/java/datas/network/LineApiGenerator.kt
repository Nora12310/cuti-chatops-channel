package datas.network

import datas.model.GroupProfile
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

class LineApiGenerator : APIGenerator("https://api.line.me/v2/bot/") {

    interface APILink {
        @GET("group/{groupId}/summary")
        suspend fun getGroupProfile(
                @Header("Authorization") token: String,
                @Path("groupId") groupId: String
        ): GroupProfile
    }
}