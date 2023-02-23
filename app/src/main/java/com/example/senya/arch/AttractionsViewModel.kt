package com.example.senya.arch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.senya.data.Attraction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(attractionsRepo: AttractionsRepo) : ViewModel() {

    private lateinit var coroutineScope: CoroutineScope

    // HomeFragment
    private val _attractionListLiveData = MutableLiveData<List<Attraction>>()
    val attractionListLiveData: LiveData<List<Attraction>> = _attractionListLiveData

    // AttractionDetailFragment
    private val _selectedAttractionLiveData = MutableLiveData<Attraction?>()
    val selectedAttractionLiveData: LiveData<Attraction?> = _selectedAttractionLiveData

    init {
        _attractionListLiveData.postValue(attractionsRepo.attractionList)
    }

    fun onAttractionSelect(attractionId: String) {

        initCoroutineScope()

        coroutineScope.launch {

            delay(1_000)

            val attraction = findAttractionById(attractionId) ?: return@launch

            _selectedAttractionLiveData.postValue(attraction)
        }

    }

    private fun initCoroutineScope() {
        coroutineScope = CoroutineScope(Dispatchers.Default)
    }

    private fun findAttractionById(attractionId: String): Attraction? {
        return attractionListLiveData.value?.find { it.id == attractionId }
    }

    fun cancelCoroutineScope() {
        if (::coroutineScope.isInitialized)
            coroutineScope.cancel()
    }

    fun resetSelectedAttractionLiveData() {
        _selectedAttractionLiveData.postValue(null)
    }

}