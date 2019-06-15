package com.gt.mynews.utils

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class Utils {

    companion object{
        var formatter: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        var formatterToQuery: DateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy")
    }
}