package com.example.aston_dev_5.placeholder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.aston_dev_5.utils.HelpersUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который представляет тестовые данные для отладки
 */
public class ContactContent {

    public static final List<ContactItem> ITEMS = new ArrayList<ContactItem>();

    private static final int COUNT = 200;

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
                HelpersUtil.generateSurname(), HelpersUtil.generatePhoneNumber(),
                "https://picsum.photos/id/" + position + "/150"
        );
    }


    /**
     * Класс для хранения информации о контакте
     */
    public static class ContactItem implements Parcelable {
        public final int id;

        public String name;
        public String surname;
        public String phoneNumber;
        public String avatarUrl;

        public ContactItem(int id, String name, String surname, String phoneNumber, String avatarUrl) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.phoneNumber = phoneNumber;
            this.avatarUrl = avatarUrl;
        }

        public ContactItem(Parcel parcel) {
            this.id = parcel.readInt();
            this.name = parcel.readString();
            this.surname = parcel.readString();
            this.phoneNumber = parcel.readString();
            this.avatarUrl = parcel.readString();
        }

        @Override
        public String toString() {
            return id + " " + name + " " + surname + " " + phoneNumber;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(name);
            parcel.writeString(surname);
            parcel.writeString(phoneNumber);
            parcel.writeString(avatarUrl);
        }

        public static final Parcelable.Creator<ContactItem> CREATOR = new Parcelable.Creator<ContactItem>() {

            @Override
            public ContactItem createFromParcel(Parcel parcel) {
                return new ContactItem(parcel);
            }

            @Override
            public ContactItem[] newArray(int i) {
                return new ContactItem[i];
            }
        };
    }
}