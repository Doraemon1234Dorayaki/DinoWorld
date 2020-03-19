package com.example.recycle

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        debugHashKey()

        val authen = authen()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.layout, authen,"fragment_authen")
        transaction.addToBackStack("fragment_authen")
        transaction.commit()
    }

    fun Login(view: View) {
        val intent = Intent(this,HomeActivity::class.java)
        startActivityForResult(intent,100)
    }

private fun debugHashKey() {
    try {
        val info = packageManager.getPackageInfo(
            "com.example.facebook_authen",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.getEncoder().encodeToString(md.digest()))
        }
    } catch (e: PackageManager.NameNotFoundException) {
    } catch (e: NoSuchAlgorithmException) {
    }
}

}
