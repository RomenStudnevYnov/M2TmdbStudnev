package com.example.m2tmdbstudnev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.m2tmdbstudnev.databinding.ActivityMainBinding
import com.example.m2tmdbstudnev.model.Person
import com.example.m2tmdbstudnev.model.PopularPersonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TMDB_API_KEY = "f8c59b73c44d9240c1ded0a07da0d5f5"

class MainActivity : AppCompatActivity() {
    val LOGTAG = MainActivity::class.simpleName
    private lateinit var binding: ActivityMainBinding
    private val peopleData: MutableList<Person> = arrayListOf()
    private val peopleAdapter = PeopleAdapter(this.peopleData)
    private var currentPage = 1
    private var totalPages = Int.MAX_VALUE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(this.binding.root)

        // People list recycler view init
        binding.people.setHasFixedSize(true)
        binding.people.layoutManager = LinearLayoutManager(this)
        binding.people.adapter = this.peopleAdapter
        binding.people.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    loadNextPage()
                }
            }
        })

        this.loadNextPage()
    }

    private fun loadPage(page: Int) {
        binding.progress.visibility = VISIBLE
        val call = ApiClient.repo.getPopularPeople(TMDB_API_KEY, page)
        call.enqueue(object : Callback<PopularPersonResponse> {
            override fun onResponse(
                call: Call<PopularPersonResponse>,
                response: Response<PopularPersonResponse>
            ) {
                Log.d(LOGTAG, "response be like: $response")
                if ((response.body()?.results?.size ?: 0) == 0) {
                    return
                }
                peopleData.addAll(response.body()!!.results)
                binding.resultCount.text = getString(
                    R.string.people_count_text,
                    peopleData.size,
                    response.body()!!.totalResults!!,
                )
                totalPages = response.body()!!.totalPages!!
                binding.progress.visibility = GONE
                peopleAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PopularPersonResponse>, t: Throwable) {
                binding.progress.visibility = GONE
                Log.e(LOGTAG, "Failed to retrieve popular people!")
            }
        })
    }

    private fun loadNextPage() {
        if (currentPage >= totalPages) {
            return
        }
        this.loadPage(currentPage++)
    }
}