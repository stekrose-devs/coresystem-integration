package com.example.memberapi.service

import com.example.memberapi.model.Member
import com.example.memberapi.repository.MemberRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any

import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.util.*

@SpringBootTest
class MemberServiceTest {
    private val memberRepository: MemberRepository = mock()
    private val service = MemberService(memberRepository)
    private val memberId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000")

    private val member: Member = Member(
        memberId = memberId,
        firstName = "first",
        lastName = "last",
        dateOfBirth = LocalDate.of(1980, 1, 1),
        addressLine1 = "line1",
        city = "city",
        state = "state",
        zipCode = "zip",
        phoneNumber = "phoneNumber",
    )

    @Test
    fun `getMemberById returns member`() {
        whenever(memberRepository.findById(any())).thenReturn(member)
        val result = service.getMemberById(memberId)
        assertEquals(member, result)
    }
}