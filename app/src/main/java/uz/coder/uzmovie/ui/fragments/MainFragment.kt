package uz.coder.uzmovie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import uz.coder.uzmovie.R
import uz.coder.uzmovie.adapters.ViewPagerAdapter
import uz.coder.uzmovie.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)


        binding.vp.adapter = ViewPagerAdapter(this)

        binding.toolbar.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.search ->{
                    findNavController().navigate(R.id.searchFragment)
                }

            }
            true
        }

        binding.bottomBar.setOnItemSelectedListener {

            when(it){
                0 ->{
                    binding.vp.currentItem = 0
                    binding.toolbar.title = "Movie Plus"
                }
                1 ->{
                    binding.vp.currentItem = 1
                    binding.toolbar.title = "Saved"
                }
                2 ->{
                    binding.vp.currentItem = 2
                    binding.toolbar.title = "Profile"
                }
            }


        }


        binding.vp.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                when (position) {
                    0 -> {
                        binding.bottomBar.itemActiveIndex = 0
                    }
                    1 -> {
                        binding.bottomBar.itemActiveIndex = 1
                    }
                    else -> {
                        binding.bottomBar.itemActiveIndex = 2
                    }
                }

            }

        })


        binding.vp.isUserInputEnabled = false

        return binding.root
    }


}