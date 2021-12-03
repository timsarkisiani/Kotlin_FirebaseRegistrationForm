package ge.tim.sarkisiani.davaleba_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPasswordRe: EditText
    private lateinit var buttonRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        init()

        registerListeners()

    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPasswordRe = findViewById(R.id.editTextPasswordRe)
        buttonRegister = findViewById(R.id.buttonRegister)
    }

    private fun registerListeners() {
        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password_re = editTextPasswordRe.text.toString()

            if(email.isEmpty()){
                Toast.makeText(this, "❗️ შეიყვანე ელ. ფოსტა", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                Toast.makeText(this, "❗️ შეიყვანე პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.length < 9){
                Toast.makeText(this, "❗️ პაროლი უნდა შეიცავდეს მინიმუმ 9 სიმბოლოს", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password_re.isEmpty()){
                Toast.makeText(this, "❗️ გაიმეორე პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password_re != password){
                Toast.makeText(this, "❗️ პაროლები არ ემთხვევა 🤔", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        startActivity(Intent(this, ProfileActivity::class.java))
                    } else {
                        Toast.makeText(this, "❌ აღნიშნული ელ. ფოსტა არავალიდური ან უკვე რეგისტრირებულია", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}