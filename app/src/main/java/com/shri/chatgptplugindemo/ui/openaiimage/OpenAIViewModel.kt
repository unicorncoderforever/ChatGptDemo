package com.shri.chatgptplugindemo.ui.openaiimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.image.ImageURL
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private val NUMBER_OF_IMAGE = 5
class OpenAIViewModel: ViewModel() {

    private var openAi: OpenAI? = null


    @OptIn(BetaOpenAI::class)
    private val _imageUri = MutableLiveData<List<ImageURL>>().apply {
        value = emptyList()
    }

    @OptIn(BetaOpenAI::class)
    val imageUri: LiveData<List<ImageURL>>  = _imageUri

    init {
        openAi = OpenAI("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")
    }

    private var _loading =  MutableLiveData<Boolean>().apply {
        value = false
    }

    val loading: LiveData<Boolean> = _loading

    @OptIn(BetaOpenAI::class)
    fun getImageFor(userText: String): LiveData<List<ImageURL>> {
        viewModelScope.launch(Dispatchers.Main) {
            _loading.value = true
            launch (Dispatchers.IO) {
                val imageUrl =  openAi?.imageURL( // or openAI.imageJSON
                    creation = ImageCreation(
                        prompt = userText,
                        n = NUMBER_OF_IMAGE,
                        size = ImageSize.is1024x1024
                    )
                )
                launch(Dispatchers.Main) {
                    _loading.value = false
                    _imageUri.value = imageUrl
                }
            }
        }
        return  imageUri
    }
}