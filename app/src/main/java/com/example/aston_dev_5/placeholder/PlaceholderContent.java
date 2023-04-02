package com.example.aston_dev_5.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class PlaceholderContent {


    public static final List<PlaceholderItem> ITEMS = new ArrayList<PlaceholderItem>();
    public static final Map<String, PlaceholderItem> ITEM_MAP = new HashMap<String, PlaceholderItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPlaceholderItem(i));
        }
    }

    private static void addItem(PlaceholderItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static PlaceholderItem createPlaceholderItem(int position) {
        return new PlaceholderItem(
                String.valueOf(position), "Данила  " + position,
                "Мерзляков " + position, "+7916667803" + position
        );
    }


    /**
     * A placeholder item representing a piece of content.
     */
    public static class PlaceholderItem {
        public final String id;

        public final String name;
        public final String surname;
        public final String phoneNumber;

        public PlaceholderItem(String id, String name, String surname, String phoneNumber) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return surname;
        }
    }
}