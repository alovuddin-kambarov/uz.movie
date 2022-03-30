package uz.coder.uzmovie.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uz.coder.uzmovie.R
import uz.coder.uzmovie.database.AppDatabase
import uz.coder.uzmovie.databinding.ActivityMain2Binding
import uz.coder.uzmovie.models.MovieClass

class MainActivity2 : AppCompatActivity() {
    private var check = false
    private lateinit var binding: ActivityMain2Binding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val movieClass = intent?.getSerializableExtra("movie") as MovieClass

        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + movieClass.backdrop_path)
            .into(binding.imageView)

        binding.caption.text = movieClass.overview
        binding.textView.text = movieClass.title
        binding.textView2.text = movieClass.title


        val database = AppDatabase.getInstants(binding.root.context).dao()

        database.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                for (i in it.indices) {
                    if (it[i].id == movieClass.id) {
                        check = true
                        binding.save.setImageResource(R.drawable.ic_baseline_bookmark_24)
                        break
                    }

                }


            }) {

            }



        binding.save.setOnClickListener {

/*

            if (!check){
                binding.save.setImageResource(R.drawable.ic_baseline_bookmark_24)
                database.add(movieClass).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
                check = true
            }else{
                binding.save.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                database.delete(movieClass.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
                check = false
            }

*/












            check = if (check) {
                binding.save.setImageResource(R.drawable.ic_baseline_bookmark_border_24)
                database.delete(movieClass.id).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
                false
            } else {
                binding.save.setImageResource(R.drawable.ic_baseline_bookmark_24)
                database.add(movieClass).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
                true
            }

        }

        binding.imageView2.setOnClickListener { onBackPressed() }

    }
}