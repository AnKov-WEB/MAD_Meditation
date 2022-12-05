package ru.kovalev_andrey.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.json.JSONException
import ru.kovalev_andrey.myapplication.R
import ru.kovalev_andrey.myapplication.models.MainModel


class MainAdapter(private val mainList: ArrayList<MainModel>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    fun setData(mainModelList: List<MainModel>) {
        this.mainList.addAll(mainModelList)
        notifyItemRangeInserted(0, mainModelList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.unit_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    /*      Для нескольких RecyclerView
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    } */

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val newImageView: ImageView = itemView.findViewById(R.id.imageView)
        private val headerTextView: TextView = itemView.findViewById(R.id.headerText)
        private val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleText)

        fun bind(main: MainModel) {
            try {
                headerTextView.text = main.getTitle()
                subtitleTextView.text = main.getBody()
                Picasso.get().load(main.getImage()).into(newImageView)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}