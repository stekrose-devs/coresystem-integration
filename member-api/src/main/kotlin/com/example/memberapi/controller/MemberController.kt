package com.example.memberapi.controller

import com.example.memberapi.model.Member
import com.example.memberapi.repository.MemberRepository
import com.example.memberapi.service.MemberService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/member")
class MemberController(private val memberService: MemberService) {

    private val log = LoggerFactory.getLogger(MemberController::class.java)

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: String): ResponseEntity<Member> {
        return try {
            val uuid = UUID.fromString(id)
            val member = memberService.getMemberById(uuid)
            if (member != null) {
                ResponseEntity.ok(member)
            } else {
                ResponseEntity.notFound().build()
            }
        } catch (ex: IllegalArgumentException) {
            log.debug("Invalid UUID supplied: {}", id)
            ResponseEntity.notFound().build()
        }
    }
}