package com.gt.mynews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Multimedium {

    @SerializedName("rank")
    @Expose
    var rank: Int? = null
    @SerializedName("subtype")
    @Expose
    var subtype: String? = null
    @SerializedName("caption")
    @Expose
    var caption: Any? = null
    @SerializedName("credit")
    @Expose
    var credit: Any? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("legacy")
    @Expose
    var legacy: Legacy? = null
    @SerializedName("subType")
    @Expose
    var subType: String? = null
    @SerializedName("crop_name")
    @Expose
    var cropName: String? = null
    @SerializedName("format")
    @Expose
    var format: String? = null
    @SerializedName("copyright")
    @Expose
    var copyright: String? = null
}
