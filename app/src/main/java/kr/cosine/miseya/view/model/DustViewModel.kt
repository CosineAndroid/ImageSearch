package kr.cosine.miseya.view.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kr.cosine.miseya.data.usecase.DustUseCase
import kr.cosine.miseya.domain.model.DustItemEntity

class DustViewModel(
    private val dustUseCase: DustUseCase
) : ViewModel() {

    private val _sidoEvent: MutableLiveData<List<DustItemEntity>> = MutableLiveData()
    val sidoEvent: LiveData<List<DustItemEntity>> get() = _sidoEvent

    private val _stationEvent: MutableLiveData<DustItemEntity?> = MutableLiveData()
    val stationEvent: LiveData<DustItemEntity?> get() = _stationEvent

    fun setSido(sidoName: String) = viewModelScope.launch {
        val stations = dustUseCase(sidoName).info.body.items ?: return@launch
        _sidoEvent.value = stations
    }

    fun setStation(stationName: String?) {
        _stationEvent.value = _sidoEvent.value?.toMutableList()?.find {
            it.stationName == stationName
        }
    }
}