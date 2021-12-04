package ge.tim.sarkisiani.davaleba_5

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.text.Layout
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import java.lang.Error

class RegistrationActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextPasswordRe: EditText
    private lateinit var buttonRegister: Button
    private lateinit var btnCheckBox: CheckBox
    private lateinit var editTextLayoutEmail: TextInputLayout
    private lateinit var editTextLayoutPassword: TextInputLayout
    private lateinit var editTextLayoutPasswordRe: TextInputLayout


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
        btnCheckBox = findViewById(R.id.btnCheckBox)
        editTextLayoutEmail = findViewById(R.id.editTextLayoutEmail)
        editTextLayoutPassword = findViewById(R.id.editTextLayoutPassword)
        editTextLayoutPasswordRe = findViewById(R.id.editTextLayoutPasswordRe)
    }

    private fun registerListeners() {
        buttonRegister.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val password_re = editTextPasswordRe.text.toString()




            if(email.isEmpty()){
                editTextLayoutEmail.error = "შეიყვანე ელ. ფოსტა"
                return@setOnClickListener
            } else {
                editTextLayoutEmail.error = null
            }



            if(password.isEmpty()){
                editTextLayoutPassword.error= "მოიფიქრე პაროლი"
                return@setOnClickListener
            } else {
                editTextPassword.error = null
            }

            if(password.length < 9){
                editTextLayoutPassword.error = "მინიმუმ 9 სიმბოლო"
                return@setOnClickListener
            } else {
                editTextPassword.error = null
            }



            if(password_re.isEmpty()){
                editTextLayoutPasswordRe.error = "გაიმეორე პაროლი"
                return@setOnClickListener
            } else {
                editTextPasswordRe.error = null
            }

            if(password_re != password){
                editTextLayoutPasswordRe.error = "პაროლები არ ემთხვევა"
                return@setOnClickListener
            } else {
                editTextPasswordRe.error = null
            }



            if(!btnCheckBox.isChecked){
                Toast.makeText(this, "❗️ დაეთანხმე წესებსა და პირობებს", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }



            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        startActivity(Intent(this, ProfileActivity::class.java))
                    } else {
                        editTextLayoutEmail.error = "ელ. ფოსტა არავალიდურია"
                    }
                }
        }
    }
}