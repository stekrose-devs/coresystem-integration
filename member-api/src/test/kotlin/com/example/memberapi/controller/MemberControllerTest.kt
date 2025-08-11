package com.example.memberapi.controller

import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `GET member with existing id returns 200 and member data`() {
        val id = "123e4567-e89b-12d3-a456-426614174000"

        mockMvc.perform(
            get("/member/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.memberId", equalTo(id)))
            .andExpect(jsonPath("$.firstName", equalTo("John")))
            .andExpect(jsonPath("$.lastName", equalTo("Doe")))
    }

    @Test
    fun `GET member with unknown id returns 404`() {
        val unknownId = "00000000-0000-0000-0000-000000000000"
        mockMvc.perform(
            get("/member/{id}", unknownId)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `GET member with invalid UUID returns 404`() {
        val invalidId = "not-a-uuid"
        mockMvc.perform(
            get("/member/{id}", invalidId)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isNotFound)
    }
}