package com.example.memberapi.repository

import com.example.memberapi.model.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class MemberRepositoryTest @Autowired constructor(
    private val repository: MemberRepository
) {

    @Test
    fun `findById returns member when id exists`() {
        val id = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")
        val member: Member? = repository.findById(id)
        assertThat(member).isNotNull
        assertThat(member!!.firstName).isEqualTo("John")
        assertThat(member.lastName).isEqualTo("Doe")
    }

    @Test
    fun `findById returns null when id does not exist`() {
        val unknownId = UUID.fromString("00000000-0000-0000-0000-000000000000")
        val member: Member? = repository.findById(unknownId)
        assertThat(member).isNull()
    }
}