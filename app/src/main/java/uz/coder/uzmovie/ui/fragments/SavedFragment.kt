package uz.coder.uzmovie.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.coder.uzmovie.adapters.MyAdapter2
import uz.coder.uzmovie.database.AppDatabase
import uz.coder.uzmovie.databinding.FragmentSavedBinding
import uz.coder.uzmovie.models.MovieClass
import uz.coder.uzmovie.ui.activities.MainActivity2

class SavedFragment : Fragment() {

    lateinit var binding: FragmentSavedBinding

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(layoutInflater)


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

        AppDatabase.getInstants(binding.root.context).dao().getAll().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({

                myAdapter2.submitList(it)


        }) {}


        binding.rv.adapter = myAdapter2

        return binding.root
    }


}