package ru.kovalev_andrey.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import ru.kovalev_andrey.myapplication.MenuActivity
import ru.kovalev_andrey.myapplication.R
import ru.kovalev_andrey.myapplication.adapters.MainAdapter
import ru.kovalev_andrey.myapplication.api.ApiConnector
import ru.kovalev_andrey.myapplication.models.MainModel
import java.io.IOException


class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<ImageButton>(R.id.hamburgerImage)?.setOnClickListener{
            val intent = Intent(activity?.applicationContext, MenuActivity::class.java)
            startActivity(intent)
        }

        val mainList = arrayListOf<MainModel>()

        val cardAdapter = MainAdapter(mainList)
        val cardRecycler = view.findViewById<RecyclerView>(R.id.unitRecycler)

        cardRecycler.layoutManager = LinearLayoutManager(cardRecycler.context)
        cardRecycler.adapter = cardAdapter

        ApiConnector().getQuotes(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val stringResponse = response.body!!.string()
                response.close()
                try {
                    val jsonResponse = JSONObject(stringResponse)
                    if (jsonResponse.getBoolean("success")) {
                        val dataJsonResponse = jsonResponse.getJSONArray("data")
                        val model: ArrayList<MainModel> = arrayListOf<MainModel>()
                        for (i in 0 until dataJsonResponse.length()) {
                            model.add(MainModel(dataJsonResponse.getJSONObject(i)))
                        }
                        cardRecycler.post { cardAdapter.setData(model) }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}