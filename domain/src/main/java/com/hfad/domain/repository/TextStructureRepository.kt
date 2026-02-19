package com.hfad.domain.repository

import com.hfad.domain.model.TextStructureDTO

interface TextStructureRepository {
    suspend fun textStructure(text: String): TextStructureDTO
}