package com.example.memberapi.service

import com.example.memberapi.model.Member
import com.example.memberapi.repository.MemberRepository
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class MemberService(
    val memberRepository: MemberRepository
) {
    fun getMemberById(id: UUID): Member? {
        return memberRepository.findById(id)
    }
}