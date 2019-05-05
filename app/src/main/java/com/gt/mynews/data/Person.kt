package com.gt.mynews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person {

    @SerializedName("firstname")
    @Expose
    var firstname: String? = null
    @SerializedName("middlename")
    @Expose
    var middlename: Any? = null
    @SerializedName("lastname")
    @Expose
    var lastname: String? = null
    @SerializedName("qualifier")
    @Expose
    var qualifier: Any? = null
    @SerializedName("title")
    @Expose
    var title: Any? = null
    @SerializedName("role")
    @Expose
    var role: String? = null
    @SerializedName("organization")
    @Expose
    var organization: String? = null
    @SerializedName("rank")
    @Expose
    var rank: Int? = null

}
