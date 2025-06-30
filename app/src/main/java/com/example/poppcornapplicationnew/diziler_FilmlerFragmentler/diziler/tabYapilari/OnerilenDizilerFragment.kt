package com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.tabYapilari

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poppcornapplicationnew.adapter.OnerilenDizilerAdapter
import com.example.poppcornapplicationnew.retrofit.ApiUtils
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShow
import com.example.poppcornapplicationnew.entities.tvShowResponse.TVShowResponse
import com.example.poppcornapplicationnew.retrofit.TVShowDaoInterface
import com.example.poppcornapplicationnew.databinding.FragmentOnerilenDizilerBinding
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.poppcornapplicationnew.diziler_FilmlerFragmentler.diziler.GenreMapper
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import okhttp3.Call as OkHttpCall
import okhttp3.Callback as OkHttpCallback
import okhttp3.Response as OkHttpResponse
import okhttp3.MediaType.Companion.toMediaType



class OnerilenDizilerFragment : Fragment() {

    interface OnOnerilenClickListener {
        fun onOnerilenClick(tvShow: TVShow)
    }

    private var clickListener: OnOnerilenClickListener? = null

    fun setOnClickListener(listener: OnOnerilenClickListener) {
        clickListener = listener
    }

    private lateinit var binding: FragmentOnerilenDizilerBinding
    private lateinit var adapter: OnerilenDizilerAdapter
    private lateinit var getTVShowDaoInterface: TVShowDaoInterface

    private lateinit var list: ArrayList<TVShow>
    private var currenPage = 1
    private var totalpage = 1
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("OnerilenDiziler Fragment", "OnerilenDiziler Fragment Açıldı")

        binding = FragmentOnerilenDizilerBinding.inflate(inflater, container, false)
        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)

        list = ArrayList()
        adapter = OnerilenDizilerAdapter(this@OnerilenDizilerFragment, list)


        binding.rv.adapter = adapter

        getTVShowDaoInterface = ApiUtils.getTVDaoInterface()
        getDiziler(currenPage)

        binding.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                if (lastVisibleItemPosition == totalItemCount - 1 && !isLoading && currenPage < totalpage) {
                    currenPage++
                    getDiziler(currenPage)
                }
            }
        })

        return binding.root
    }

    private fun getDiziler(page: Int) {
        isLoading = true
        getTVShowDaoInterface.getTvShow(page = page).enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                response.body()?.let {
                    totalpage = it.totalPages
                    val newList = it.results.filterNot { new -> list.any { old -> old.id == new.id } }

                    //  AI tahmini al
                    predictWithAI(newList) { filteredList ->
                        list.addAll(filteredList)
                        adapter.notifyDataSetChanged()
                    }
                }
                isLoading = false
            }

            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                t.printStackTrace()
                isLoading = false
            }
        })
    }




    fun predictWithAI(tvList: List<TVShow>, callback: (List<TVShow>) -> Unit) {
        Log.d("AI Predict", " Başladı. Toplam gelen dizi sayısı: ${tvList.size}")

        if (tvList.isEmpty()) {
            Log.d("AI Predict", " Dizi listesi boş, geri dönülüyor.")
            activity?.runOnUiThread { callback(emptyList()) }
            return
        }

        val jsonArray = JSONArray()
        val indexList = mutableListOf<Int>()
        val validTVList = mutableListOf<TVShow>()

        for (tv in tvList) {
            val obj = JSONObject()
            obj.put("puan", tv.voteAverage)

            val turler = JSONArray()
            tv.genreIds.forEach { id ->
                GenreMapper.genreMap[id]?.let { turler.put(it) }
            }

            if (turler.length() > 0) {
                obj.put("turler", turler)
                jsonArray.put(obj)
                indexList.add(validTVList.size)
                validTVList.add(tv)

                Log.d("AI Predict", " Gönderilecek dizi: ${tv.name} | Türler: $turler | Puan: ${tv.voteAverage}")
            } else {
                Log.w("AI Predict", " TÜR EKSİK! Dizi: ${tv.name} | genreIds: ${tv.genreIds}")
            }
        }

        if (jsonArray.length() == 0) {
            Log.w("AI Predict", " Tahmine gönderilecek hiçbir dizi yok.")
            activity?.runOnUiThread { callback(emptyList()) }
            return
        }

        val requestBody = RequestBody.create(
            "application/json".toMediaType(),
            jsonArray.toString()
        )

        val request = Request.Builder()
            .url("http://192.168.1.143:5000/predict_batch")
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).enqueue(object : OkHttpCallback {
            override fun onResponse(call: OkHttpCall, response: OkHttpResponse) {
                response.body?.string()?.let { body ->
                    val results = JSONArray(body)
                    val filteredList = ArrayList<TVShow>()

                    Log.d("AI Predict", " AI'den toplam $results.length() tahmin geldi")

                    for (i in 0 until results.length()) {
                        val item = results.getJSONObject(i)
                        val prediction = item.getString("prediction")
                        val rawScore = item.getDouble("raw_score")

                        val originalIndex = indexList[i]
                        val tv = validTVList[originalIndex]

                        Log.d("AI Predict", " Dizi: ${tv.name} | Tahmin: $prediction | Skor: $rawScore")

                        if (prediction == "Çok Beğenir" || prediction == "Beğenir") {
                            filteredList.add(tv)
                            Log.d("AI Predict", " Eklendi: ${tv.name}")
                        } else {
                            Log.d("AI Predict", " Atlandı (Beğenmez): ${tv.name}")
                        }
                    }

                    Log.d("AI Predict", " Toplam önerilen dizi sayısı: ${filteredList.size}")

                    activity?.runOnUiThread {
                        callback(filteredList)
                    }
                }
            }

            override fun onFailure(call: OkHttpCall, e: IOException) {
                Log.e("AI Predict", " Hata: ${e.message}")
                activity?.runOnUiThread {
                    callback(emptyList())
                }
            }
        })
    }









}
