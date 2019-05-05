package com.gt.mynews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Keyword {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("value")
    @Expose
    var value: String? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("major")
    @Expose
    var major: String? = null

}
