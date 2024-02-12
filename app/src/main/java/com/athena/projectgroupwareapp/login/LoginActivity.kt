package com.athena.projectgroupwareapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.athena.projectgroupwareapp.main.MainActivity
import com.athena.projectgroupwareapp.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

//로그인할때 계정을 따로 만들자

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener{
            clickLogin()
        }

    }//onCreate

    //로그인 버튼 눌렀을때 실행되느 함수
    private fun clickLogin() {
        var id: String = binding.id.text.toString()
        var password: String = binding.password.text.toString()
        Log.i("id", id + password)

        var firebese: FirebaseFirestore = FirebaseFirestore.getInstance()


        firebese.collection("employeeList")
            .whereEqualTo("id", id)
            .whereEqualTo("password", password)
            .get().addOnSuccessListener {
                if (it.documents.size > 0) {
                    //사이즈가 1개 이상이라는것은 즉 null이 아니다.
                    var id: String = it.documents[0].id
                    var name: String = it.documents[0].get("name").toString()
                    var imgProfile: String = it.documents[0].get("profileUrl").toString()
                    var team: String = it.documents[0].get("team").toString()


                    G.employeeAccount = EmployeeAccount(id, name, imgProfile, team)
                    //로그인시 사원번호와 이름, 이미지Url을 G에 넣어준다. 나중에 메신저에서 가져와야함.

                    var intent = Intent(this, MainActivity::class.java)

                    //백스택을 깔끔하게 없애고 화면 전환하자
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                    startActivity(intent)

                } else {
                    AlertDialog.Builder(this).setMessage("ID와 비밀번호를 다시 확인해주세요").show()

                    binding.password.setText("")
                    binding.id.setText("")
                    binding.id.requestFocus()

                    //키보드 내리기
                    var inputManager: InputMethodManager =
                        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(
                        this.currentFocus?.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS
                    )

                }

            }
    }


}//LoginActivity




