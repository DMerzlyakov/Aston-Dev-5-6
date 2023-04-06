package com.example.aston_dev_5.utils

import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.widget.ImageView
import com.example.aston_dev_5.R
import com.squareup.picasso.Callback
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Утилиты, которые помогают в работе приложения
 */
object HelpersUtil {
    private val firstNames = arrayOf(
        "Александр",
        "Андрей",
        "Антон",
        "Артем",
        "Владимир",
        "Дмитрий",
        "Егор",
        "Иван",
        "Кирилл",
        "Максим",
        "Никита",
        "Олег",
        "Петр",
        "Роман",
        "Сергей",
        "Тимур",
        "Фёдор",
        "Юрий",
        "Ярослав"
    )
    private val lastNames = arrayOf(
        "Иванов",
        "Смирнов",
        "Кузнецов",
        "Попов",
        "Васильев",
        "Петров",
        "Соколов",
        "Михайлов",
        "Новиков",
        "Федоров",
        "Морозов",
        "Волков",
        "Алексеев",
        "Лебедев",
        "Семёнов",
        "Егоров",
        "Павлов",
        "Козлов",
        "Степанов",
        "Николаев"
    )
    private val random = Random()

    /**
     * Проверка на размерность экрана для расположения двух фрагментов сразу
     */
    @JvmStatic
    fun isScreenForTwoFragments(resources: Resources): Boolean {
        return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ||
                resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    /**
     * Генерация случайного имени
     */
    @JvmStatic
    fun generateName(): String {
        return firstNames[random.nextInt(firstNames.size)]
    }

    /**
     * Генерация случайной фамилии
     */
    @JvmStatic
    fun generateSurname(): String {
        return lastNames[random.nextInt(lastNames.size)]
    }

    /**
     * Генерация случайного номера
     */
    @JvmStatic
    fun generatePhoneNumber(): String {
        val phoneNumber = StringBuilder("+7")
        for (i in 0..9) {
            phoneNumber.append(random.nextInt(10))
        }
        return phoneNumber.toString()
    }
}

/** Функция-расширение для загрузки аватара по URL */
fun ImageView.setImageFromUrl(url: String) {
    Picasso.get()
        .load(url)
        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
        .transform(CircularTransformation(0))
        .error(R.drawable.image_not_available)
        .fit()
        .into(this, object : Callback {
            override fun onSuccess() {}
            override fun onError(e: Exception) {
                Log.e("Error on download Avatar", e.message.toString())
            }
        })
}
