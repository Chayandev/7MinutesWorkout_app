package com.example.need7minute

class ExerciseModel(
    private var id: Int,
    private var exName: String,
    private var ImageName: Int,
    private var isCompleted: Boolean=false,
    private var isSelected: Boolean=false

) {
    fun getId(): Int {
        return id;
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getExName(): String {
        return exName
    }

    fun setExName(name: String) {
        this.exName = name
    }

    fun getImgName(): Int {
        return ImageName
    }

    fun setImgName(imageName: Int) {
        this.ImageName = imageName
    }

    fun getIsCompleted(): Boolean {
        return isCompleted
    }

    fun setIsCompleted(complete: Boolean) {
        this.isCompleted=complete
    }

    fun getIsSelected(): Boolean {
        return isSelected
    }

    fun setIsSelected(select: Boolean) {
        this.isSelected=select
    }
}