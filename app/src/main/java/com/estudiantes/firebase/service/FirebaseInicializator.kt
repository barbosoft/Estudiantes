package com.estudiantes

import android.content.Context
import android.util.Log
import com.google.firebase.FirebaseOptions
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

private const val TAG = "FirebaseIncializator"

fun inicializatorFirebase(context: Context) {
    val options = FirebaseOptions.Builder()
        .setProjectId("estudiante-831bd")
        .setApplicationId("1:137210535639:android:da11f24cfe57d989ec5a92")
        .build()
    try {
        Firebase.initialize(context, options, "bd-firebase")
    }
    catch (e: Exception) {
        Log.e(TAG, "FirebaseIncializator: ", e)
    }
}