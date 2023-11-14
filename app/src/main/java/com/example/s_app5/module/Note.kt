package com.example.s_app5.module

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.util.UUID

data class Note @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id:UUID= UUID.randomUUID(),
    val title:String,
    val description:String,
    val entryData:LocalDateTime=LocalDateTime.now()
)
