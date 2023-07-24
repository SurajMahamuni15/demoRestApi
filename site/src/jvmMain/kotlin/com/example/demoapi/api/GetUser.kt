package com.example.demoapi.api

import com.example.demoapi.model.ApiResponse
import com.example.demoapi.model.User
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

private val users = listOf(
    User("suraj", 20),
    User("vivek", 40),
    User("raj", 20),
    User("ajay", 20),
    User("dev", 20),
    User("rahul", 20)
)

@Api
suspend fun getUser(context: ApiContext) {
    try {
        val name = context.req.params.getValue("name").toString()
        users.forEach { user: User ->
            if (user.name == name) {
                context.res.setBodyText(Json.encodeToString<ApiResponse>(ApiResponse.Success(user)))
            }
        }

    } catch (e: Exception) {
        context.res.setBodyText(Json.encodeToString<ApiResponse>(ApiResponse.Error(e.message.toString())))
    }
}

