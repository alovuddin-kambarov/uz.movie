package uz.coder.uzmovie.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.coder.uzmovie.adapters.MyAdapter2
import uz.coder.uzmovie.databinding.FragmentSearchBinding
import uz.coder.uzmovie.models.MovieClass
import uz.coder.uzmovie.ui.activities.MainActivity2
import uz.coder.uzmovie.utils.Status
import uz.coder.uzmovie.viewmodels.MovieViewModel


class SearchFragment : Fragment() {

    lateinit var binding:FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)


        val movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        val myAdapter2 = MyAdapter2(object : MyAdapter2.OnItemMenuClick {
            override fun onMenuClick(category: MovieClass) {
                startActivity(
                    Intent(
                        binding.root.context,
                        MainActivity2::class.java
                    ).putExtra("movie", category)
                )
            }
        })

        binding.search.addTextChangedListener {

            if (it!!.isNotBlank()){

                binding.close.visibility = View.VISIBLE
                movieViewModel.getSearchVideo(binding.root.context, it.toString()).observe(viewLifecycleOwner){

                    when (it.status) {
                        Status.SUCCESS -> {
                            myAdapter2.submitList(it.data!!.results)
                            binding.rv.adapter = myAdapter2

                        }
                        Status.ERROR -> {

                        }
                        Status.LOADING -> {

                        }
                    }

                }


            }else{
                binding.close.visibility = View.GONE
            }

        }

        binding.close.setOnClickListener {
            findNavController().popBackStack()
        }


        return binding.root
    }


}