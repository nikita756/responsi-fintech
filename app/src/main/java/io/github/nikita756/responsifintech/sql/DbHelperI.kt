package io.github.nikita756.responsifintech.sql

import io.github.nikita756.responsifintech.entities.QrResult



interface DbHelperI {

    fun insertQRResult(result: String): Int

    fun getQRResult(id: Int): QrResult


    fun deleteQrResult(id: Int): Int

    fun getAllQRScannedResult(): List<QrResult>

    fun deleteAllQRScannedResult()

}