package com.example.aston_dev_5.placeholder;

import com.example.aston_dev_5.HelpersUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, который представляет тестовые данные для отладки
 */
public class ContactContent {

    public static final List<ContactItem> ITEMS = new ArrayList<ContactItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 0; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    private static void addItem(ContactItem item) {
        ITEMS.add(item);
    }

    private static ContactItem createPlaceholderItem(int position) {
        return new ContactItem(
                position, HelpersUtil.generateName(),
                HelpersUtil.generateSurname(), HelpersUtil.generatePhoneNumber()
        );
    }


    /**
     * Класс для хранения информации о контакте
     */
    public static class ContactItem {
        public final int id;

        public String name;
        public String surname;
        public String phoneNumber;

        public ContactItem(int id, String name, String surname, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return id + " " + name + " " + surname + " " + phoneNumber;
        }

        public void editItem(String name, String surname, String phoneNumber) {
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;

        }
    }
}