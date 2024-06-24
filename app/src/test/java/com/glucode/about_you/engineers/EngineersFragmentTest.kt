package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.enums.SortBy
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
class EngineersFragmentTest {

    private lateinit var binding: FragmentEngineersBinding
    private lateinit var navController: TestNavHostController
    private lateinit var fragmentScenario: FragmentScenario<EngineersFragment>

    @Before
    fun setUp() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        fragmentScenario = launchFragmentInContainer(themeResId = R.style.Theme_AboutYou) {
            EngineersFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        navController.setGraph(R.navigation.navigation_main)
                        Navigation.setViewNavController(fragment.requireView(), navController)
                    }
                }
            }
        }
        binding = FragmentEngineersBinding.bind(fragmentScenario.fragment.requireView())
    }

    @Test
    fun engineersListIsDisplayed() {
        onView(withId(R.id.list)).check(matches(isDisplayed()))
        val recyclerView = binding.list as RecyclerView
        assertEquals(MockData.engineers.size, recyclerView.adapter?.itemCount)
    }


    @Test
    fun clickEngineerNavigatesToAbout() {
        val engineerToClick = MockData.engineers[0]
        onView(withId(R.id.list)).perform(
            RecyclerViewActions.actionOnItem<EngineersRecyclerViewAdapter.ViewHolder>(
                hasDescendant(withText(engineerToClick.name)), click()
            )
        )

        assertEquals(R.id.aboutFragment, navController.currentDestination?.id)
        val bundle = navController.currentBackStackEntry?.arguments
        assertEquals(engineerToClick.name, bundle?.getString("name"))
    }

    // TODO More tests for different sort orders, edge
}
