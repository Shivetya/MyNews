package com.gt.mynews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("docs")
    @Expose
    var docs: List<Doc>? = null
    @SerializedName("meta")
    @Expose
    var meta: Meta? = null

}
