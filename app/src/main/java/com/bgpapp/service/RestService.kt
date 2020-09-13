package com.bgpapp.service

import com.bgpapp.ui.addevent.model.EventAddResponse
import com.bgpapp.ui.addevent.model.EventToAdd
import com.bgpapp.ui.events.model.EventBEItem
import com.bgpapp.ui.login.LoginResponse
import com.bgpapp.ui.login.UserToLoginData
import com.bgpapp.ui.pubs.Pub
import com.bgpapp.ui.pubs.model.PubBE
import com.bgpapp.ui.registration.CreatedUser
import com.bgpapp.ui.registration.RegisterData
import com.bgpapp.ui.wikipedia.model.WikipediaBEItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestService {

    @POST("user/register")
    suspend fun registerUser(@Body userRegistrationDto: RegisterData): CreatedUser

    @POST("user/login")
    suspend fun loginUser(@Body userToLoginData: UserToLoginData): LoginResponse

    @GET("meeting/")
    suspend fun getEvents(): List<EventBEItem>

    @GET("game/")
    suspend fun getGames(): List<WikipediaBEItem>

    @GET("pub/")
    suspend fun getPubs() : List<PubBE>

    @POST("meeting/add")
    suspend fun addEvent(@Body eventToAdd: EventToAdd): EventAddResponse

    @POST("pub/add")
    suspend fun addPub(@Body pub: Pub)

}