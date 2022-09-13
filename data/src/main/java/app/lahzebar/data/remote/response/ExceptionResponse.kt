package app.lahzebar.data.remote.response

import com.google.gson.annotations.SerializedName

data class ExceptionResponse(

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("message")
    val message: String
)
