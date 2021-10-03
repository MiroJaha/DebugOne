package com.example.debugone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var llMain: LinearLayout
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var password2: EditText
    private lateinit var submitBtn: Button
    private lateinit var activeUsers: TextView

    private var users = arrayListOf(
        "Freddy",
        "Jason",
        "Ripley",
        "Poncho",
        "Saitama",
        "Archer",
        "Derek",
        "Pamela",
        "Simba",
        "Simon",
        "Retsy",
        "Peter",
        "Daria",
        "Smitty"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llMain = findViewById(R.id.llMain)
        userName = findViewById(R.id.etUsername)
        password = findViewById(R.id.etPassword)
        password2 = findViewById(R.id.etConfirmPassword)
        submitBtn = findViewById(R.id.btSubmit)
        activeUsers = findViewById(R.id.tvActiveUsers)
        submitBtn.setOnClickListener {
            if(coloring()) {
                Toast.makeText(this, "User created!", Toast.LENGTH_LONG).show()
                users.add(userName.text.toString().capitalize())
                displayUsers()
            }
        }
    }

    private fun coloring():Boolean{
        var boolName = false
        var boolPass = false
        if(usernameAccepted(userName.text.toString())) {
            userName.setBackgroundColor(Color.GREEN)
            boolName = true
        }
        else
            userName.setBackgroundColor(Color.RED)
        if(passwordAccepted(password.text.toString())){
            password.setBackgroundColor(Color.GREEN)
            password2.setBackgroundColor(Color.GREEN)
            boolPass = true
        }
        else {
            password.setBackgroundColor(Color.RED)
            password2.setBackgroundColor(Color.RED)
        }
        return boolName&&boolPass
    }

    private fun usernameAccepted(text: String): Boolean{
        var contain = true
        for (i in users)
            if (i.equals(text,true))
                contain = false
        if(contain){
            if(text.length in 5..15){
                if(!hasNumber(text)){
                    if(!hasSpecial(text) && !text.contains(" ")){
                        return true
                    }
                    Toast.makeText(this, "The username cannot contain special characters or spaces", Toast.LENGTH_LONG).show()
                }
                else
                    Toast.makeText(this, "The username cannot contain numbers", Toast.LENGTH_LONG).show()
            }
            else
                Toast.makeText(this, "The username must be between 5 and 15 characters long", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, "The username is already taken", Toast.LENGTH_LONG).show()
        return false
    }

    private fun passwordAccepted(text: String): Boolean{
        if (text == password2.text.toString()) {
            if (text.length >= 8) {
                if (hasUpper(text)) {
                    if (hasNumber(text)) {
                        if (hasSpecial(text)) {
                            return true
                        }
                        Toast.makeText(
                            this,
                            "The password must contain a special character",
                            Toast.LENGTH_LONG
                        ).show()
                    } else
                        Toast.makeText(
                            this,
                            "The password must contain a number",
                            Toast.LENGTH_LONG
                        ).show()
                } else
                    Toast.makeText(
                        this,
                        "The password must contain an uppercase letter",
                        Toast.LENGTH_LONG
                    ).show()
            } else
                Toast.makeText(
                    this,
                    "The password must be at least 8 characters long",
                    Toast.LENGTH_LONG
                ).show()
        }else
            Toast.makeText(
                this,
                "The confirm password must match the entered password",
                Toast.LENGTH_LONG
            ).show()
        return false
    }

    private fun hasUpper(text: String): Boolean{
        var letter = 'A'
        while (letter <= 'Z') {
            for (i in text)
                if(i == letter){
                    return true
                }
            ++letter
        }
        return false
    }

    private fun hasNumber(text: String): Boolean{
        for(i in 0..9){
            if(text.contains(i.toString())){
                return true
            }
        }
        return false
    }

    private fun hasSpecial(text: String): Boolean{
        val specialCharacters = "!@#$%"
        for(special in specialCharacters){
            if(text.contains(special)){
                return true
            }
        }
        return false
    }

    private fun displayUsers(){
        var allUsers = "Active Users:\n\n"
        for(user in users){
            allUsers += user + "\n"
        }
        llMain.isVisible = false
        activeUsers.text = allUsers
        activeUsers.isVisible = true
    }
}