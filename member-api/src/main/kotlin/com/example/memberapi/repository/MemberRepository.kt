package com.example.memberapi.repository

import com.example.memberapi.model.Member
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.IOException
import java.util.UUID

@Component
class MemberRepository(
    @Value("classpath:members.json") private val dataFile: Resource,
    private val objectMapper: ObjectMapper
) {

    private val log = LoggerFactory.getLogger(MemberRepository::class.java)
    private val members: Map<UUID, Member> = loadMembers()

    fun findById(id: UUID): Member? = members[id]

    private fun loadMembers(): Map<UUID, Member> {
        return try {
            dataFile.inputStream.use { inputStream ->
                val listType = object : TypeReference<List<Member>>() {}
                val membersList: List<Member> = objectMapper.readValue(inputStream, listType)
                membersList.associateBy { it.memberId }
            }
        } catch (ex: IOException) {
            log.error("Failed to load members from JSON file: {}", ex.message)
            emptyMap()
        }
    }
}