package com.example.aston_dev_5

import android.content.res.Configuration
import android.content.res.Resources
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
    private const val urlImageRandom = "https://picsum.photos/300/300"
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

    /**
     * Генерация случайной аватарки
     */
    @JvmStatic
    fun generateImage():  {
        val phoneNumber = StringBuilder("+7")
        for (i in 0..9) {
            phoneNumber.append(random.nextInt(10))
        }
        return phoneNumber.toString()
    }

}
