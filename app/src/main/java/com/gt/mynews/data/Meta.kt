package com.gt.mynews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Meta {

    @SerializedName("hits")
    @Expose
    var hits: Int? = null
    @SerializedName("offset")
    @Expose
    var offset: Int? = null
    @SerializedName("time")
    @Expose
    var time: Int? = null

}
