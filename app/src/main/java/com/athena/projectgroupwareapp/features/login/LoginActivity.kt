package com.athena.projectgroupwareapp.features.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.base.BaseActivity
import com.athena.projectgroupwareapp.databinding.ActivityLoginBinding
import com.athena.projectgroupwareapp.main.MainActivity
import com.athena.projectgroupwareapp.features.login.viewmodel.LoginViewModel
import com.athena.projectgroupwareapp.model.EmployeeAccount
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun getViewModel(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding.btnLogin.setOnClickListener {
            clickLogin()
        }
        Log.d("LOGIN", "Login Activity ID: ${G.employeeAccount?.id}")
    }

    private fun clickLogin() {
        var id: String = binding.id.text.toString()
        var password: String = binding.password.text.toString()

        var firebese: FirebaseFirestore = FirebaseFirestore.getInstance()
        firebese.collection("employeeList")
            .whereEqualTo("id", id)
            .whereEqualTo("password", password)
            .get().addOnSuccessListener {
                if (it.documents.size > 0) {
                    var id: String = it.documents[0].id
                    var name: String = it.documents[0].get("name").toString()
                    var imgProfile: String = it.documents[0].get("profileUrl").toString()
                    var team: String = it.documents[0].get("team").toString()

                    G.employeeAccount = EmployeeAccount(id, name, imgProfile, team)

                    var intent = Intent(this, MainActivity::class.java)

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                    startActivity(intent)

                } else {
                    AlertDialog.Builder(this).setMessage("ID와 비밀번호를 다시 확인해주세요").show()

                    binding.password.setText("")
                    binding.id.setText("")
                    binding.id.requestFocus()

                    var inputManager: InputMethodManager =
                        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(
                        this.currentFocus?.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )
                }
            }
    }
}