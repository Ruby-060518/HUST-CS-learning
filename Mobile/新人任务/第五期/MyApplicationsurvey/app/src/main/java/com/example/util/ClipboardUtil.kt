package com.example.util

import android.content.Context

object ClipboardUtil {
    fun readText(context: Context): String? {
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as
                android.content.ClipboardManager
        return cm.primaryClip?.getItemAt(0)?.coerceToText(context)?.toString()
    }
}