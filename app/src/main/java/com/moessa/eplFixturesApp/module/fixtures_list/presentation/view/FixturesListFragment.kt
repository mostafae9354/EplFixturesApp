package com.moessa.eplFixturesApp.module.fixtures_list.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.moessa.eplFixturesApp.R
import com.moessa.eplFixturesApp.databinding.FragmentDocsListBinding
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.adapter.FixturesAdapter
import com.moessa.eplFixturesApp.module.fixtures_list.presentation.viewmodel.FixturesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FixturesListFragment : Fragment() {

    private lateinit var binding: FragmentDocsListBinding
    private val viewModel by viewModels<FixturesViewModel>()
    private lateinit var adapter: FixturesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_docs_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeStateFlow()
        setupUI()
        viewModel.getFixturesList()
    }

    private fun setupUI() {
        binding.rvDocs.layoutManager = LinearLayoutManager(context)
        binding.rvDocs.setHasFixedSize(true)
        adapter = FixturesAdapter()
        binding.rvDocs.addItemDecoration(
            DividerItemDecoration(
                binding.rvDocs.context,
                (binding.rvDocs.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.rvDocs.adapter = adapter
    }

    private fun observeStateFlow() {

        lifecycleScope.launchWhenStarted {

            launch {
                viewModel.fixturesListStateFlow().collect { list ->
                    if (!list.isNullOrEmpty()) {
                        binding.rvDocs.visibility = View.VISIBLE
                        adapter.submitList(ArrayList(list))
                    }
                }
            }

            launch {
                viewModel.errorStateFlow().collect {
                    if (it != null) {
                        Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                        it.printStackTrace()
                    }
                }
            }

            launch {
                viewModel.loadingStateFlow().collect {
                    binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FixturesListFragment()
    }
}