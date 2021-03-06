package com.logituit.moviedbmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.logituit.moviedbmvvm.api.RetrofitHelper
import com.logituit.moviedbmvvm.database.QuoteDataBase
import com.logituit.moviedbmvvm.databinding.ActivityMainBinding
import com.logituit.moviedbmvvm.details.DetailsViewp
import com.logituit.moviedbmvvm.details.DetailsViewu
import com.logituit.moviedbmvvm.repository.QuoteRepository
import com.logituit.moviedbmvvm.viewmodel.popularadapter
import com.logituit.moviedbmvvm.viewmodel.upcomingadapter
import com.logituit.moviedbmvvm.models.Result
import com.logituit.mvvm.models.ResultX
import com.logituit.mvvm.viewmodel.MainViewModel
import com.logituit.mvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var newRecyclerView: RecyclerView
   // private lateinit var popularMovieAdapter: Myadapter
   //private lateinit var upcomingMoviesAdapter: Myadapter1
   private lateinit var newadapter:popularadapter
   private lateinit var newadapter1:upcomingadapter
    lateinit var newArrayList: ArrayList<Result>
    lateinit var newArrayList1: ArrayList<ResultX>
    lateinit var binding: ActivityMainBinding
//    private var isChange: Boolean = false
//    var i: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //val rview: RecyclerView = binding.popolarR
//        rview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollHorizontally(1)) {
//                    ++i
//                    getUserdata()
//                    // isChange=true
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//
//                super.onScrolled(recyclerView, dx, dy)
//            }
//        })


        val quoteService = RetrofitHelper.moviesInstance
        val quoteDataBase= QuoteDataBase.getdatabase(this)
        val repository = QuoteRepository(quoteService, quoteDataBase)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository,quoteDataBase)).get(MainViewModel::class.java)

       // mainViewModel.quotes.observe(this) { Log.d("MAHESH", it.results.toString()) }
        //mainViewModel.quotes1.observe(this) { Log.d("AMRAVATI", it.results.toString()) }
        getMovies()
        getMovies1()
       // getUserdata()
        //getUserdata1()
    }

//    private fun getUserdata1() {
//        newRecyclerView = binding.upcomingR
//        newRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        newRecyclerView.setHasFixedSize(true)
//        newArrayList1 = arrayListOf()
//
//        upcomingMoviesAdapter= Myadapter1(this@MainActivity, newArrayList1)
//        newRecyclerView.adapter = upcomingMoviesAdapter
//            mainViewModel.quotes1.observe(this) {
//
//                upcomingMoviesAdapter.clearData()
//                upcomingMoviesAdapter.addData(it.results)
//                upcomingMoviesAdapter.notifyDataSetChanged()
//            }
//    }
//
//    private fun getMovies(){
//        lifecycleScope.launch{ mainViewModel.getMovies().collectLatest{Result-> popularadapter.submitData(Result) } }
//    }
//    private fun getUserdata() {
//        newRecyclerView = binding.popolarR
//        newRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        newRecyclerView.setHasFixedSize(true)
//        newArrayList = arrayListOf()
//    popularMovieAdapter= Myadapter(this@MainActivity, newArrayList)
//    newRecyclerView.adapter = popularMovieAdapter
//    mainViewModel.quotes.observe(this) {
//
//        popularMovieAdapter.clearData1()
//        popularMovieAdapter.addData1(it.results)
//        popularMovieAdapter.notifyDataSetChanged()
//    }
//
//
//    }
    private fun getMovies(){
        newRecyclerView = binding.popolarR
        newRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf()

        newadapter = popularadapter(::popularadapterOnClick)
        newRecyclerView.adapter = newadapter
        mainViewModel.list.observe(this) { newadapter.submitData(lifecycle, it) }
}
    private fun getMovies1() {
        newRecyclerView = binding.upcomingR
        newRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        newRecyclerView.setHasFixedSize(true)
        newArrayList1 = arrayListOf()
        newadapter1 = upcomingadapter(::upcomingadapterOnClick)
        newRecyclerView.adapter = newadapter1
        mainViewModel.list1.observe(this){newadapter1.submitData(lifecycle,it)}
    }
    private fun popularadapterOnClick(movieViewItem: Result) {
        val bottomSheetDialogFragment = DetailsViewp()
        bottomSheetDialogFragment.arguments = DetailsViewp.with(movieViewItem)
        bottomSheetDialogFragment.show(this.supportFragmentManager,"")
    }
    private fun upcomingadapterOnClick(movieViewItem: ResultX) {
        val bottomSheetDialogFragment = DetailsViewu()
        bottomSheetDialogFragment.arguments = DetailsViewu.with(movieViewItem)
        bottomSheetDialogFragment.show(this.supportFragmentManager,"")
    }

}