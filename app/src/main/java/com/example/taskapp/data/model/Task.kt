package com.example.taskapp.data.model

import android.os.Parcelable
import com.example.taskapp.utils.FirebaseHelper
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    var id:String="",
    var description:String="",
    var status: Status=Status.TODO
):Parcelable{
    init{
        this.id = FirebaseHelper.getDataBase().push().key ?: ""
    }
}
