package com.glucode.about_you.about.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import coil.load
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileBinding
import com.glucode.about_you.mockdata.MockData

class ProfileCardView
    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
    ) : CardView(context, attrs, defStyleAttr) {
        private val binding: ViewProfileBinding =
            ViewProfileBinding.inflate(LayoutInflater.from(context), this)
        private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>

        var name: String? = null
            set(value) {
                field = value
                binding.name.text = value
            }

        var role: String? = null
            set(value) {
                field = value
                binding.role.text = value
            }

        var image: String? = null
            set(value) {
                field = value
                binding.profileImage.load(value) {
                    crossfade(true)
                    placeholder(R.drawable.ic_person)
                }
            }

        init {
            radius = resources.getDimension(R.dimen.corner_radius_normal)
            elevation = resources.getDimension(R.dimen.elevation_normal)
            setCardBackgroundColor(ContextCompat.getColor(context, R.color.black))

            binding.profileImage.setOnClickListener {
                pickImageFromGallery()
            }
        }

        fun registerImagePicker(fragment: Fragment) {
            pickImageLauncher =
                fragment.registerForActivityResult(
                    ActivityResultContracts.StartActivityForResult(),
                ) { result ->
                    if (result.resultCode == Activity.RESULT_OK) {
                        image = result.data?.data.toString()
                        MockData.updateEngineerImage(name!!, image!!)
                    }
                }
        }

        private fun pickImageFromGallery() {
            val intent =
                Intent(Intent.ACTION_PICK).apply {
                    type = "image/*"
                }
            pickImageLauncher.launch(intent)
        }
    }
