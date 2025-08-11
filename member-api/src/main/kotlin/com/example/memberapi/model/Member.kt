package com.example.memberapi.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.util.UUID

data class Member(
    @JsonProperty("memberId")
    val memberId: UUID,

    @JsonProperty("firstName")
    val firstName: String,

    @JsonProperty("lastName")
    val lastName: String,

    @JsonProperty("dateOfBirth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    val dateOfBirth: LocalDate,

    @JsonProperty("addressLine1")
    val addressLine1: String,

    @JsonProperty("city")
    val city: String,

    @JsonProperty("state")
    val state: String,

    @JsonProperty("zipCode")
    val zipCode: String,

    @JsonProperty("phoneNumber")
    val phoneNumber: String
)