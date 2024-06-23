package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData

class EngineersFragment : Fragment() {
    private lateinit var binding: FragmentEngineersBinding
    private lateinit var adapter: EngineersRecyclerViewAdapter

    private var currentSort: SortBy = SortBy.NONE

    enum class SortBy {
        NONE, YEARS, COFFEES, BUGS
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpEngineersList(MockData.engineers)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        currentSort = when (item.itemId) {
            R.id.action_years -> SortBy.YEARS
            R.id.action_coffees -> SortBy.COFFEES
            R.id.action_bugs -> SortBy.BUGS
            else -> SortBy.NONE
        }
        sortEngineers()
        return super.onOptionsItemSelected(item)
    }

    private fun sortEngineers() {
        val sortedEngineers = when (currentSort) {
            SortBy.YEARS -> MockData.engineers.sortedBy { it.quickStats.years }
            SortBy.COFFEES -> MockData.engineers.sortedBy { it.quickStats.coffees }
            SortBy.BUGS -> MockData.engineers.sortedBy { it.quickStats.bugs }
            else -> MockData.engineers
        }
        adapter.updateEngineers(sortedEngineers)
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {
        adapter = EngineersRecyclerViewAdapter(engineers) { goToAbout(it) }
        binding.list.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}