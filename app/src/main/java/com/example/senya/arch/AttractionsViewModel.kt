package com.example.senya.arch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttractionsViewModel @Inject constructor(private val attractionsRepo: AttractionsRepo) :
    ViewModel() {

}