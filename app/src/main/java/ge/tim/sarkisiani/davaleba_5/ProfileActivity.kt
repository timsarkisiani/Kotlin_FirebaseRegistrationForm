package ge.tim.sarkisiani.davaleba_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var usermail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        init()
        usermail.text = FirebaseAuth.getInstance().currentUser?.email
    }

    private fun init() {
        usermail = findViewById(R.id.usermail)
    }
}