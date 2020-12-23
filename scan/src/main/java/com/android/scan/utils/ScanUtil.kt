package com.android.scan.utils

import android.content.Context
import com.honeywell.aidc.AidcManager
import com.honeywell.aidc.BarcodeReader
import com.honeywell.aidc.InvalidScannerNameException
import java.util.*

/**
 * @author: hec@shuyilink.com
 * @date:   2020/12/23
 * @since:
 */
class ScanUtil private constructor() {

    var barcodeReader: BarcodeReader? = null
    private var manager: AidcManager? = null

    companion object {
        val instance = ScanUtil()
    }

    fun register(context: Context, barcodeListener: BarcodeReader.BarcodeListener? = null) {
        if (manager != null && barcodeReader != null) {
            return
        }
        AidcManager.create(context) { manager ->
            this.manager = manager
            try {
                barcodeReader = manager.createBarcodeReader()
                barcodeReader?.addBarcodeListener(barcodeListener)
                val properties = initProperties()
                // Apply the settings
                // Apply the settings
                barcodeReader?.setProperties(properties)
            } catch (e: InvalidScannerNameException) {
            } catch (e: Exception) {
            }
        }
    }

    fun unRegister() {
        barcodeReader?.close()
        manager?.close()
    }

    private fun initProperties(): MutableMap<String, Any> {
        val properties: MutableMap<String, Any> =
            HashMap()
        // Set Symbologies On/Off
        // Set Symbologies On/Off
        properties[BarcodeReader.PROPERTY_CODE_128_ENABLED] = true
        properties[BarcodeReader.PROPERTY_GS1_128_ENABLED] = true
        properties[BarcodeReader.PROPERTY_QR_CODE_ENABLED] = true
        properties[BarcodeReader.PROPERTY_CODE_39_ENABLED] = true
        properties[BarcodeReader.PROPERTY_DATAMATRIX_ENABLED] = true
        properties[BarcodeReader.PROPERTY_UPC_A_ENABLE] = true
        properties[BarcodeReader.PROPERTY_EAN_13_ENABLED] = false
        properties[BarcodeReader.PROPERTY_AZTEC_ENABLED] = false
        properties[BarcodeReader.PROPERTY_CODABAR_ENABLED] = false
        properties[BarcodeReader.PROPERTY_INTERLEAVED_25_ENABLED] = false
        properties[BarcodeReader.PROPERTY_PDF_417_ENABLED] = false
        // Set Max Code 39 barcode length
        // Set Max Code 39 barcode length
        properties[BarcodeReader.PROPERTY_CODE_39_MAXIMUM_LENGTH] = 10
        // Turn on center decoding
        // Turn on center decoding
        properties[BarcodeReader.PROPERTY_CENTER_DECODE] = true
        // Enable bad read response
        // Enable bad read response
        properties[BarcodeReader.PROPERTY_NOTIFICATION_BAD_READ_ENABLED] = true
        return properties
    }
}