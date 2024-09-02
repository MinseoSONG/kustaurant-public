package com.kust.kustaurant.domain.usecase.detail

import com.kust.kustaurant.data.model.CommentDataResponse
import com.kust.kustaurant.domain.repository.DetailRepository
import javax.inject.Inject

class PostCommentReportUseCase @Inject constructor(
    private val detailRepository: DetailRepository
) {
    suspend operator fun invoke(restaurant: Int, commentId: Int) {
        return detailRepository.postCommentReport(restaurant, commentId)
    }
}
