package uz.coder.uzmovie.adaptersimport android.view.LayoutInflaterimport android.view.Viewimport android.view.ViewGroupimport androidx.recyclerview.widget.RecyclerViewimport androidx.viewpager2.widget.ViewPager2import com.squareup.picasso.Callbackimport com.squareup.picasso.Picassoimport uz.coder.uzmovie.databinding.ViewPagerItemBindingimport uz.coder.uzmovie.models.MovieClassimport java.lang.Exceptionclass SliderAdapter(private val arraylist: ArrayList<MovieClass>, val viewPager2: ViewPager2, val onClick: OnClick) :    RecyclerView.Adapter<SliderAdapter.ViewH>() {    inner class ViewH(var binding: ViewPagerItemBinding) : RecyclerView.ViewHolder(binding.root) {        fun onBind(myClass: MovieClass) {            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + myClass.poster_path).into(binding.image, object :Callback{                override fun onSuccess() {                    binding.shimmerView.visibility = View.GONE                }                override fun onError(e: Exception?) {                }            })            if (position == arraylist.size - 1) {                viewPager2.post(runnable)            }            binding.title.text = myClass.title            binding.info.text = myClass.overview            itemView.setOnClickListener {                onClick.click(myClass)            }        }    }    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {        return ViewH(            ViewPagerItemBinding.inflate(                LayoutInflater.from(parent.context),                parent,                false            )        )    }    override fun getItemCount(): Int {        return arraylist.size    }    override fun onBindViewHolder(holder: ViewH, position: Int) {        holder.onBind(arraylist[position])    }    private val runnable = Runnable {        arraylist.addAll(arraylist)        notifyDataSetChanged()    }    interface OnClick{        fun click(movieClass: MovieClass)    }}