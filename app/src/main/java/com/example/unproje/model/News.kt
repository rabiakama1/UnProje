package com.example.unproje.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class News :  Serializable {

    @SerializedName("id")
    private var  id: String?=null
    @SerializedName("source")
    private var source: Any?=null
    @SerializedName("name")
    private var  name: String?=null
    @SerializedName("description")
    private var description: String? = null
    @SerializedName("title")
    private var title: String? = null
    @SerializedName("urlToImage")
    private  var urlToImage: String?=null
    @SerializedName("content")
    private  var content: String?=null


    fun getTitle(): String? {
        return title
    }



    fun getDesc(): String? {
        return description

    }

    fun setDesc(description: String?) {
        this.description = description
    }
    fun getPos(): String? {
        return urlToImage
    }

    fun setPos(urlToImage: String?) {
        this.urlToImage = urlToImage
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }



}
