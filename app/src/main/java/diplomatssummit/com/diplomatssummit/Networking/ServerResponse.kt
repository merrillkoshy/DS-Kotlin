package diplomatssummit.com.diplomatssummit.Networking

import com.google.gson.annotations.SerializedName


class ServerResponse {

    // variable name should be same as in the json response from php
    @SerializedName("success")
    var success: Boolean = false
        internal set
    @SerializedName("message")
    var message: String? = null
        internal set

}