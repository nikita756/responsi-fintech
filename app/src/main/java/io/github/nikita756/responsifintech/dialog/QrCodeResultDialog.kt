package io.github.nikita756.responsifintech.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings.Global.getString
import android.text.ClipboardManager
import android.util.Log
import android.widget.Toast

import io.github.nikita756.responsifintech.R
import io.github.nikita756.responsifintech.entities.QrResult
import io.github.nikita756.responsifintech.sql.DbHelper
import io.github.nikita756.responsifintech.sql.DbHelperI
import io.github.nikita756.responsifintech.sql.QrResultDataBase
import io.github.nikita756.responsifintech.utils.ContentCheckUtil.isWebUrl
import io.github.nikita756.responsifintech.utils.toFormattedDisplay
import kotlinx.android.synthetic.main.fragment_result.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


class QrCodeResultDialog(var context: Context) {

    private lateinit var dialog: Dialog

    private lateinit var dbHelperI: DbHelperI

    private var qrResult: QrResult? = null

    private var onDismissListener: OnDismissListener? = null

    init {
        init()
        initDialog()
    }


    private fun init() {
        dbHelperI = DbHelper(QrResultDataBase.getAppDatabase(context)!!)
    }

    private fun initDialog() {
        dialog = Dialog(context)
        dialog.setContentView(R.layout.fragment_result)
        dialog.setCancelable(false)
        onClicks()
    }

    private fun onClicks() {



        dialog.copyResult.setOnClickListener {
            copyResultToClipBoard()
        }

        dialog.shareResult.setOnClickListener {
            shareResult()
        }

        dialog.cancelDialog.setOnClickListener {
            dialog.dismiss()
            onDismissListener?.onDismiss()
        }

        dialog.scannedText.setOnClickListener {
            checkContentAndPerformAction(dialog.scannedText.text.toString())
        }
    }





    fun show(recentQrResult: QrResult) {
        this.qrResult = recentQrResult
        dialog.scannedDate.text = qrResult?.calendar?.toFormattedDisplay()
        dialog.scannedText.text = "QR Code: " + qrResult!!.result
        dialog.show()

        // Fetch Qr code Data via api
        val url = "https://qr-scanner-api.herokuapp.com/api/user/" + qrResult!!.result
        dialog.userInfo1.text = ""
        dialog.userInfo2.text = context.getString(R.string.loading);
        dialog.userInfo3.text = ""
        dialog.userInfo4.text = ""

        // Run in another thread until completion to avoid thread blocking
        doAsync{
            val json = URL(url).readText()
            uiThread {
                val obj = JSONObject(json)

                try {

                    if (obj.getString("status") == "success"){

                        dialog.userInfo1.text = "Name: \n" + obj.getJSONObject("data").getString("name")
                        dialog.userInfo2.text = "Email: \n" + obj.getJSONObject("data").getString("email")
                        dialog.userInfo3.text = "Field: \n" + obj.getJSONObject("data").getString("field")
                        dialog.userInfo4.text = "Website: \n" + obj.getJSONObject("data").getString("website")
                    }
                    else if (obj.getString("status") == "fail"){

                        dialog.userInfo1.text = ""
                        dialog.userInfo2.text = obj.getString("message")
                        dialog.userInfo3.text = ""
                        dialog.userInfo4.text = ""
                    }
                }
                catch (e: NumberFormatException) {

                    Log.e("Tag", e.toString())
                }
            }
        }

    }

    fun setOnDismissListener(dismissListener: OnDismissListener) {
        this.onDismissListener = dismissListener
    }

    private fun copyResultToClipBoard() {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("QrCodeScannedResult", dialog.scannedText.text)
        clipboard.text = clip.getItemAt(0).text.toString()
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    private fun shareResult() {
        val txtIntent = Intent(Intent.ACTION_SEND)
        txtIntent.type = "text/plain"
        txtIntent.putExtra(
            Intent.EXTRA_TEXT,
            dialog.scannedText.text.toString()
        )
        context.startActivity(Intent.createChooser(txtIntent, "Share QR Result"))
    }

    interface OnDismissListener {
        fun onDismiss()
    }

    // Checking content type and performing action on it.
    private fun checkContentAndPerformAction(scannedText: String) {
        when {

            // if it is web url
            isWebUrl(scannedText) -> {

                // opening web url.
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(scannedText)
                context.startActivity(i)
            }
        }
    }
}