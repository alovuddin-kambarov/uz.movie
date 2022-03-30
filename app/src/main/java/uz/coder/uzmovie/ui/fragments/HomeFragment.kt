package uz.coder.uzmovie.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import uz.coder.uzmovie.R
import uz.coder.uzmovie.adapters.MyAdapter
import uz.coder.uzmovie.adapters.SliderAdapter
import uz.coder.uzmovie.databinding.FragmentHomeBinding
import uz.coder.uzmovie.models.MovieClass
import uz.coder.uzmovie.ui.activities.MainActivity2
import uz.coder.uzmovie.utils.Status
import uz.coder.uzmovie.viewmodels.MovieViewModel
import kotlin.math.abs


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dialog: AlertDialog
    private lateinit var movieViewModel: MovieViewModel
    var handler = Handler(Looper.myLooper()!!)

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)




        setProgressDialog()


        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]



        getPopularVideo()

        getPopularVideo2()

        setHandleViewPager()


        return binding.root
    }

    private fun setProgressDialog() {
        dialog = AlertDialog.Builder(binding.root.context).create()
        val view = LayoutInflater.from(binding.root.context)
            .inflate(R.layout.custom_progress, null, false)
        dialog.setView(view)
        dialog.setContentView(view)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
        dialog.show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPopularVideo2() {

        val arrayList = ArrayList<MovieClass>()
        val sliderAdapter =
            SliderAdapter(arrayList, binding.viewPager2, object : SliderAdapter.OnClick {
                override fun click(movieClass: MovieClass) {
                    startActivity(
                        Intent(
                            binding.root.context,
                            MainActivity2::class.java
                        ).putExtra("movie", movieClass)
                    )
                }
            })

        movieViewModel.getPopularVideo(binding.root.context, 2).observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {

                    dialog.cancel()
                    arrayList.addAll(it.data!!.results)
                    sliderAdapter.notifyDataSetChanged()

                }
                Status.ERROR -> {
                    dialog.cancel()
                    Log.d(tag, it.message.toString())

                }
                Status.LOADING -> {

                }
            }


        }

        binding.viewPager2.adapter = sliderAdapter

    }

    private val runnable =
        Runnable { binding.viewPager2.currentItem = binding.viewPager2.currentItem + 1 }


    private fun setHandleViewPager() {


        binding.viewPager2.clipToPadding = false
        binding.viewPager2.clipChildren = false
        binding.viewPager2.offscreenPageLimit = 3
        binding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer = CompositePageTransformer()

        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->

            val r = 1 - abs(position)

            page.scaleY = 0.85f + r * 0.15f

        }


        binding.viewPager2.setPageTransformer(compositePageTransformer)

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 3000)

            }
        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPopularVideo() {
        val arrayList2 = ArrayList<MovieClass>()

        val myAdapter = MyAdapter(arrayList2, object : MyAdapter.OnClick {
            override fun click(myClass: MovieClass) {
                startActivity(
                    Intent(
                        binding.root.context,
                        MainActivity2::class.java
                    ).putExtra("movie", myClass)
                )
            }
        })
        movieViewModel.getPopularVideo(binding.root.context, 4).observe(viewLifecycleOwner) {

            when (it.status) {
                Status.SUCCESS -> {

                    dialog.cancel()
                    arrayList2.addAll(it.data!!.results)
                    arrayList2.reverse()
                    myAdapter.notifyDataSetChanged()

                }
                Status.ERROR -> {
                    dialog.cancel()
                    Log.d(tag, it.message.toString())

                }
                Status.LOADING -> {

                }
            }


        }

        binding.rv.adapter = myAdapter
    }


}