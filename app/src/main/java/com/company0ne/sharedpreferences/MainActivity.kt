package com.company0ne.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.edit1)
        age = findViewById(R.id.edit2)
    }

    // Fetch the stored data in onResume() Because this is what will be called when the app opens again
    override fun onResume() {
        super.onResume()
        // Fetching the stored data from the SharedPreference
        val sharePreference = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val userName = sharePreference.getString("name", "")
        val userAge = sharePreference.getInt("age", 0)

        // Setting the fetched data in the EditTexts
        name.setText(userName)
        age.setText(userAge.toString())
    }

    // Store the data in the SharedPreference in the onPause() method
    // When the user closes the application onPause() will be called and data will be stored
    override fun onPause() {
        super.onPause()
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name.text.toString())
        myEdit.putInt("age", age.text.toString().toInt())
        myEdit.apply()
        Toast.makeText(this , "Data is saved",Toast.LENGTH_SHORT).show()
    }
}
