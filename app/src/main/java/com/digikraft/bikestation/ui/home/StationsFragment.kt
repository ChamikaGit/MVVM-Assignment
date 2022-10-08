package com.digikraft.bikestation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.digikraft.bikestation.R
import com.digikraft.bikestation.databinding.FragmentMainBinding
import com.digikraft.bikestation.model.bike.Feature
import com.digikraft.bikestation.ui.adapters.StationAdapter
import com.digikraft.bikestation.utils.Resource
import com.digikraft.bikestation.viewmodel.MainSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationsFragment : Fragment(), StationAdapter.ItemClickLister {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainSharedViewModel by activityViewModels()
    private lateinit var stationAdapter: StationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadBikeData(type = "pub_transport", co = "stacje_rowerowe")

        initRecyclerView()
        viewModelObserver()
    }

    private fun initRecyclerView() {
        stationAdapter = StationAdapter(this)
        val mLayoutManager = LinearLayoutManager(requireActivity())
        binding.apply {
            bikeRecyclerView.apply {
                layoutManager = mLayoutManager
                adapter = stationAdapter
            }
        }
    }

    private fun viewModelObserver() {
        viewModel.bikeDetailsResponseLiveData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    response.data?.let {
                        stationAdapter.setFeatureList(ArrayList(it.features))
                    }
                }
                is Resource.Error -> {
                    binding.progressbar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun itemClick(feature: Feature) {
        val direction = StationsFragmentDirections.stationsFragmentToStationsDetailsFragment(feature = feature)
        findNavController().navigate(direction)
    }

}