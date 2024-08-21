package com.example.artimo_emotion_diary

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException

import android.content.Intent


class EmojiAdapter(private val context: Context, private val imageList: List<String>) :
    RecyclerView.Adapter<EmojiAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_emoji, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageFileName = imageList[position]

        try {
            // 이미지 파일을 AssetManager를 사용하여 읽어옴
            val assetManager: AssetManager = context.assets
            val inputStream = assetManager.open(imageFileName)
            val drawable = Drawable.createFromStream(inputStream, null)
            holder.imageView.setImageDrawable(drawable)
            inputStream.close()

            // 이미지를 클릭할 때의 동작 설정
            holder.imageView.setOnClickListener {
                val intent = Intent(context, WriteActivity::class.java)

                // 이미지 파일 이름을 전달
                intent.putExtra("emoji", imageFileName)

                context.startActivity(intent)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
