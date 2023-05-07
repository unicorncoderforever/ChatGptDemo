package com.shri.chatgptplugindemo.ui.openaiimage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aallam.openai.api.BetaOpenAI
import com.shri.chatgptdemo.databinding.OpenAiImageLayoutBinding
import com.shri.chatgptplugindemo.ui.openaiimage.imageadapter.ImageAdapter

class OpenAIImageFragment : Fragment() {

    private var _binding: OpenAiImageLayoutBinding? = null

    private val binding get() = _binding!!

    @OptIn(BetaOpenAI::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = OpenAiImageLayoutBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val homeViewModel =
            ViewModelProvider(this).get(OpenAIViewModel::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val imageAdapter = homeViewModel.imageUri.value?.let {
            ImageAdapter(it)
        } ?: ImageAdapter(emptyList())

        binding.recyclerView.adapter = imageAdapter
        binding.submitButton.setOnClickListener{
            binding.editText.text?.let {
                homeViewModel.getImageFor(it.toString())
            }
        }


        homeViewModel.imageUri.observe(viewLifecycleOwner) {
                imageAdapter.setImageUrlList(it)
        }


        homeViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBarLayout.isVisible = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}