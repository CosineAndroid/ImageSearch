package kr.cosine.miseya.data.usecase

import kr.cosine.miseya.data.repository.DustRepository
import kr.cosine.miseya.domain.model.DustEntity
import kr.cosine.miseya.domain.mapper.toEntity

class DustUseCase(
    private val dustRepository: DustRepository
) {

    suspend operator fun invoke(stationName: String): DustEntity {
        return dustRepository.getDust(stationName).toEntity()
    }
}