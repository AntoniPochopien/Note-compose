package com.example.note.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConteverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String{
        return uuid.toString()
    }

    @TypeConverter
    fun toUUID(stringUUID: String): UUID{
        return  UUID.fromString(stringUUID)
    }
}