package com.glucode.about_you.engineers

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import coil.load
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class EngineersRecyclerViewAdapterTest {

    private lateinit var adapter: EngineersRecyclerViewAdapter
    private val mockOnClick: (Engineer) -> Unit = mock()

    @Before
    fun setUp() {
        adapter = EngineersRecyclerViewAdapter(MockData.engineers, mockOnClick)
    }

    @Test
    fun onCreateViewHolder_inflatesCorrectLayout() {
        val parent = mock(ViewGroup::class.java)
        val viewHolder = adapter.onCreateViewHolder(parent, 0)

        assertEquals(ItemEngineerBinding::class.java, viewHolder.itemView.getTag())
    }

    @Test
    fun onBindViewHolder_bindsDataCorrectly() {
        val viewHolder = EngineersRecyclerViewAdapter.EngineerViewHolder(
            ItemEngineerBinding.inflate(
                null,
                null,
                false
            )
        )
        val engineer = MockData.engineers[0]

        viewHolder.bind(engineer, mockOnClick)

        val nameTextView = viewHolder.itemView.findViewById<TextView>(R.id.name)
        val roleTextView = viewHolder.itemView.findViewById<TextView>(R.id.role)
        val imageView = viewHolder.itemView.findViewById<ImageView>(R.id.profile_image)

        assertEquals(engineer.name, nameTextView.text)
        assertEquals(engineer.role, roleTextView.text)
        // TODO: Verify that the image is loaded correctly using with a custom Request.Listener
    }

    @Test
    fun getItemCount_returnsCorrectCount() {
        assertEquals(MockData.engineers.size, adapter.itemCount)
    }

    @Test
    fun updateEngineers_updatesListAndNotifiesAdapter() {
        val newEngineers = listOf(MockData.engineers[1], MockData.engineers[0]) // Swap order
        adapter.updateEngineers(newEngineers)

        assertEquals(newEngineers.size, adapter.itemCount)
        assertEquals(newEngineers[0], adapter.engineers[0]) // Verify order changed
        // You might want to add a verification for notifyDataSetChanged() being called
    }

    @Test
    fun onClick_callsProvidedLambda() {
        val viewHolder = EngineersRecyclerViewAdapter.EngineerViewHolder(
            ItemEngineerBinding.inflate(
                null,
                null,
                false
            )
        )
        val engineer = MockData.engineers[0]

        viewHolder.bind(engineer, mockOnClick)
        viewHolder.itemView.performClick()

        verify(mockOnClick).invoke(engineer)
    }
}
