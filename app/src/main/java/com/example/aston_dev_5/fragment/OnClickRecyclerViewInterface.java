package com.example.aston_dev_5.fragment;

import com.example.aston_dev_5.placeholder.ContactContent;

/**
 * Вспомогательный интерфейс для обработки нажатий в RecyclerView
 */
interface OnClickRecyclerViewInterface {
    void onItemClick(ContactContent.ContactItem item);
}
