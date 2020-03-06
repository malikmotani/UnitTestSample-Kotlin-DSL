package com.unittestsample.app.presentation.user

import com.google.gson.Gson
import com.unittestsample.app.data.user.entity.RandomUserApiResponse

const val RANDOM_USER_JSON = " {\n" +
        "     \"data\": [{\n" +
        "         \"id\": 1,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/women/2.jpg\",\n" +
        "         \"name\": \"beatrice\"\n" +
        "     }, {\n" +
        "         \"id\": 2,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/79.jpg\",\n" +
        "         \"name\": \"arvid\"\n" +
        "     }, {\n" +
        "         \"id\": 3,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/95.jpg\",\n" +
        "         \"name\": \"okan\"\n" +
        "     }, {\n" +
        "         \"id\": 4,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/23.jpg\",\n" +
        "         \"name\": \"yıldırım\"\n" +
        "     }, {\n" +
        "         \"id\": 5,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/31.jpg\",\n" +
        "         \"name\": \"florent\"\n" +
        "     }, {\n" +
        "         \"id\": 6,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/38.jpg\",\n" +
        "         \"name\": \"logan\"\n" +
        "     }, {\n" +
        "         \"id\": 7,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/42.jpg\",\n" +
        "         \"name\": \"charlotte\"\n" +
        "     }, {\n" +
        "         \"id\": 8,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/23.jpg\",\n" +
        "         \"name\": \"ermelinda\"\n" +
        "     }, {\n" +
        "         \"id\": 9,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/44.jpg\",\n" +
        "         \"name\": \"molly\"\n" +
        "     }, {\n" +
        "         \"id\": 10,\n" +
        "         \"avatarUrl\": \"https://randomuser.me/api/portraits/men/1.jpg\",\n" +
        "         \"name\": \"molly\"\n" +
        "     }]\n" +
        " }"

val gson = Gson()

fun randomUserResponse(): RandomUserApiResponse =
    gson.fromJson<RandomUserApiResponse>(RANDOM_USER_JSON, RandomUserApiResponse::class.java)
