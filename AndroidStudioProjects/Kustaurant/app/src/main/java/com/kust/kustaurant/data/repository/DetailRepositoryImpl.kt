package com.kust.kustaurant.data.repository


import com.kust.kustaurant.data.model.CommentDataResponse
import com.kust.kustaurant.data.model.DetailDataResponse
import com.kust.kustaurant.data.model.EvaluationDataRequest
import com.kust.kustaurant.data.model.EvaluationDataResponse
import com.kust.kustaurant.data.remote.DetailApi
import com.kust.kustaurant.domain.repository.DetailRepository
import com.kust.kustaurant.presentation.ui.mypage.CommentData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailApi: DetailApi
) : DetailRepository {
    override suspend fun getDetailData(restaurantId: Int) : DetailDataResponse{
        return detailApi.getDetailData(restaurantId)
    }

    override suspend fun getCommentData(restaurantId: Int, sort: String) : List<CommentDataResponse>{
        return detailApi.getCommentData(restaurantId, sort)
    }

    override suspend fun postCommentData(restaurantId: Int, commentId : Int, inputText : String) : CommentDataResponse{
        return detailApi.postCommentData(restaurantId, commentId, inputText)
    }

    override suspend fun postFavoriteToggle(restaurantId: Int) : Boolean{
        return detailApi.postFavoriteToggle(restaurantId)
    }

    override suspend fun getEvaluationData(restaurantId: Int): EvaluationDataResponse {
        return detailApi.getEvaluationData(restaurantId)
    }

    override suspend fun postEvaluationData(
        restaurantId: Int,
        evaluationScore: RequestBody,
        evaluationSituations: List<MultipartBody.Part>,
        evaluationComment: RequestBody?,
        newImage: MultipartBody.Part?
    ): DetailDataResponse {
        return detailApi.postEvaluationData(
            restaurantId,
            evaluationScore,
            evaluationSituations,
            evaluationComment,
            newImage
        )
    }
}