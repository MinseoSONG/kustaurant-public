package com.example.kustaurant.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.kustaurant.R
import com.example.kustaurant.data.model.DetailDataResponse
import com.example.kustaurant.domain.usecase.GetDetailDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailDataUseCase: GetDetailDataUseCase
): ViewModel() {
    val tabList = MutableLiveData(listOf("메뉴", "리뷰"))

    private val _detailData = MutableLiveData<DetailDataResponse>()
    val detailData: LiveData<DetailDataResponse> = _detailData

    private val _menuData = MutableLiveData<List<MenuData>>()
    val menuData: LiveData<List<MenuData>> = _menuData

    private val _tierData = MutableLiveData<TierInfoData>()
    val tierData: LiveData<TierInfoData> = _tierData


    fun loadDetailData(restaurantId : Int) {
        viewModelScope.launch {
            val getDetailData = getDetailDataUseCase(restaurantId)
            _detailData.value = getDetailData
            _menuData.value = getDetailData.restaurantMenuList.map{
                MenuData(it.menuId, it.menuName, it.menuPrice, it.naverType, it.menuImgUrl)
            }
            _tierData.value = TierInfoData(getDetailData.restaurantCuisineImgUrl, getDetailData.restaurantCuisine,
                getDetailData.mainTier, getDetailData.situationList)
        }
    }
}