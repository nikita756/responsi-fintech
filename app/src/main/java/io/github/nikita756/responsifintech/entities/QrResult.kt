package io.github.nikita756.responsifintech.entities

import androidx.room.*
import io.github.nikita756.responsifintech.converter.DateTimeConverters
import java.sql.Timestamp
import java.util.*


@Entity
@TypeConverters(DateTimeConverters::class)
data class QrResult(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "result")
    val result: String?,

    @ColumnInfo(name = "result_type")
    val resultType: String ,

    @ColumnInfo(name = "favourite")
    val favourite: Boolean = false,

    @ColumnInfo(name = "time")
    val calendar: Calendar

)