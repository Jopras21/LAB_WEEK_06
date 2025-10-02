// Lokasi: app/src/main/java/com/example/lab_week_06/CatViewHolder.kt
package com.example.lab_week_06

import ImageLoader
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

// Simpan konstanta di luar kelas atau di dalam companion object
private const val FEMALE_SYMBOL = "\u2640"
private const val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"

// LANGKAH 1: Definisikan interface kustom di sini (di luar kelas)
// Ini adalah "kontrak" untuk siapa saja yang ingin mendengarkan klik pada item kucing.


class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    // LANGKAH 2: Gunakan interface kustom kita, BUKAN View.OnClickListener
    private val onclickListener: CatAdapter.OnClickListener
) : RecyclerView.ViewHolder(containerView) {

    private val catBiographyView: TextView by lazy {
        containerView.findViewById(R.id.cat_biography)
    }
    private val catBreedView: TextView by lazy {
        containerView.findViewById(R.id.cat_breed)
    }
    private val catGenderView: TextView by lazy {
        containerView.findViewById(R.id.cat_gender)
    }
    private val catNameView: TextView by lazy {
        containerView.findViewById(R.id.cat_name)
    }
    private val catPhotoView: ImageView by lazy {
        containerView.findViewById(R.id.cat_photo)
    }

    fun bindData(cat: CatModel) {
        // Atur OnClickListener untuk seluruh item view
        containerView.setOnClickListener {
            // LANGKAH 3: Panggil fungsi dari interface kustom kita
            onclickListener.onItemClick(cat)
        }

        imageLoader.loadImage(cat.imageUrl, catPhotoView)
        catNameView.text = cat.name
        catBreedView.text = when (cat.breed) {
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
            else -> "Unknown"
        }

        catBiographyView.text = cat.biography
        catGenderView.text = when (cat.gender) {
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            else -> UNKNOWN_SYMBOL
        }
    }

}