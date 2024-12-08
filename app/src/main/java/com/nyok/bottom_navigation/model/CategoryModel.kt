package com.nyok.bottom_navigation.model

class CategoryModel(
    private val id: Int,
    private val title: String,
    private val imageResId: Int,
    private var isSelected: Boolean
) {
    // Getter for id
    fun getId(): Int {
        return id
    }

    // Getter for title
    fun getTitle(): String {
        return title
    }

    // Getter for imageResId
    fun getImageResId(): Int {
        return imageResId
    }

    // Getter for isSelected
    fun isSelected(): Boolean {
        return isSelected
    }

    // Setter for isSelected
    fun setSelected(selected: Boolean) {
        isSelected = selected
    }
}



