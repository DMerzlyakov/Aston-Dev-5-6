package com.example.aston_dev_5;

import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Random;

/**
 * Утилиты, которые помогают в работе приложения
 */
public class HelpersUtil {

    static private final String[] firstNames = {"Александр", "Андрей", "Антон", "Артем", "Владимир", "Дмитрий", "Егор", "Иван", "Кирилл", "Максим", "Никита", "Олег", "Петр", "Роман", "Сергей", "Тимур", "Фёдор", "Юрий", "Ярослав"};
    static private final String[] lastNames = {"Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов", "Михайлов", "Новиков", "Федоров", "Морозов", "Волков", "Алексеев", "Лебедев", "Семёнов", "Егоров", "Павлов", "Козлов", "Степанов", "Николаев"};

    static private final Random random = new Random();


    /**
     * Проверка на размерность экрана для расположения двух фрагментов сразу
     */
    public static boolean isScreenForTwoFragments(Resources resources) {
        return (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ||
                (resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }

    /**
     * Генерация случайного имени
     */
    public static String generateName() {
        return firstNames[random.nextInt(firstNames.length)];
    }

    /**
     * Генерация случайной фамилии
     */
    public static String generateSurname() {
        return lastNames[random.nextInt(lastNames.length)];
    }

    /**
     * Генерация случайного номера
     */
    public static String generatePhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder("+7");
        for (int i = 0; i < 10; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }


}
