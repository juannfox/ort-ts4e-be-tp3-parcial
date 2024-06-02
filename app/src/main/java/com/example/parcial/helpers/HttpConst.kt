package com.example.parcial.helpers

import android.opengl.GLES32.DebugProc
import retrofit2.http.Query

object HttpConst {
    const val URL_BASE = "https://d9811bf4-5e67-4a8c-bdcf-603cbbfc0275.mock.pstmn.io/"
    const val API_KEY_PARAM = "api_key"
    const val API_KEY = "123" // En un mundo ideal, esto vendria de un proveedor de secretos :)
    const val SEARCH_ENGINE_KEY = "engine"
    const val SEARCH_ENGINE = "google_flights"
    const val DEPARTURE_ID_KEY_PARAM = "departure_id"
    const val ARRIVAL_ID_KEY_PARAM = "arrival_id"
    const val OUTBOUND_DATE_KEY_PARAM = "outbound_date"
    const val RETURN_DATE_KEY_PARAM = "return_date"
}