package com.example.zozamax_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.zozamax_app.R
import com.example.zozamax_app.databinding.MovieRecBinding
import com.example.zozamax_app.databinding.MovieRightBinding
import com.example.zozamax_app.data.Result

class MovieAdapter(val movies: ArrayList<DummyModel>,val cont: Context): RecyclerView.Adapter<MovieAdapter.MyHolder>() {
    private var layoutType: Int? = null

    inner class MyHolder(val binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(model: DummyModel) {
            when (binding) {
                is MovieRecBinding -> {
                    binding.title.text = model.title
                    binding.description.text = model.overview

                    binding.transformationLayout.transitionName = model.title

                    binding.root.setOnClickListener{
                        val navCont = binding.root.findNavController()
                        navCont.navigate(R.id.action_homeFragment_to_movieFragment)

                    }
                }
                is MovieRightBinding -> {
                    binding.title.text = model.title
                    binding.description.text = model.overview

                    binding.transformationLayout.transitionName = model.title

                    binding.root.setOnClickListener{
                        val navCont = binding.root.findNavController()
                        navCont.navigate(R.id.action_homeFragment_to_movieFragment)

                    }
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MyHolder {
        val binding = if (layoutType == 0) {
            MovieRecBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            MovieRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        }
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MyHolder, position: Int) {
        layoutType = position % 2

        holder.bindData(movies[position])

    }

    override fun getItemCount() = movies.size
}

class DummyModel(
    val title: String,
    val overview:String
)

class items{
    val model = DummyModel(
        title = "Game of thrones",
        overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model1 = DummyModel(
        title = "Big Mouth",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model2 = DummyModel(
        title = "The Godfather",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model3 = DummyModel(
        title = "Inception ",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model4 = DummyModel(
        title = "The dark knight",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model5 = DummyModel(
        title = "Forest Gump",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model6 = DummyModel(
        title = "Fight club",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model7 = DummyModel(
        title = "Titanic",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model8 = DummyModel(
        title = "The matrix",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")
 val model9 = DummyModel(
        title = "Jurassic park",
     overview = "Game of Thrones follows a large cast of characters and interwoven story arcs. It is primarily set on the fictional continent of Westeros, which is divided into the Seven Kingdoms and the lands in the far North beyond “the Wall,” an enormous wall of fortified ice")

    val listMovies = arrayListOf(
    model, model1, model2,model3,model4,model5,model6,model7,model8,model9
)

}