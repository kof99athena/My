package com.athena.projectgroupwareapp.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.view.size
import com.athena.projectgroupwareapp.main.MainActivity
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityDailyBinding
import com.athena.projectgroupwareapp.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.getField

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    var collectionPath : String = "employee"
    var documentPath : String = "ManageHeadquarters"
    var undercollectionPath : String = "HR"



    //로그인 버튼 눌렀을때 실행되느 함수
    private fun clickLogin(){
        var id : String = binding.id.text.toString()
        var password : String = binding.password.text.toString()
        Log.i("id",id+password)

        var firebese : FirebaseFirestore = FirebaseFirestore.getInstance()


        firebese.collection(collectionPath).document(documentPath).collection(undercollectionPath )
            .whereEqualTo("ID",id)
            .whereEqualTo("password",password)
            .get().addOnSuccessListener {
                if(it.documents.size>0){
                    //사이즈가 1개 이상이라는것은 즉 null이 아니다.
                    var id : String = it.documents[0].id
                    var name : String = it.documents[0].get("name").toString()
                    var imgProfile : String = it.documents[0].get("profileUrl").toString()

                    G.employeeAccount = EmployeeAccount(id,name,imgProfile )
                    //로그인시 사원번호와 이름, 이미지Url을 G에 넣어준다. 나중에 메신저에서 가져와야함.

                    var intent = Intent(this, MainActivity::class.java)

                    //백스택을 깔끔하게 없애고 화면 전환하자
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)


                    startActivity(intent)

                }else{
                    AlertDialog.Builder(this).setMessage("ID와 비밀번호를 다시 확인해주세요").show()

                    binding.password.setText("")
                    binding.id.setText("")
                    binding.id.requestFocus()

                    //키보드 내리기
                    var inputManager : InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(this.currentFocus?.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)

                }


            }


    }  //관리부문 - 인사팀

    private fun clickLogin2(){
        collectionPath  = "employee"
        documentPath  = "ManageHeadquarters"
        undercollectionPath = "accounting"
        clickLogin()
    } //관리부문 - 회계팀

    private fun clickLogin3(){
        collectionPath  = "employee"
        documentPath  = "ManageHeadquarters"
        undercollectionPath = "compliance"
        clickLogin()
    } //관리부문 - 컴플라이언스팀

    private fun clickLogin4(){
        collectionPath  = "employee"
        documentPath  = "ITHeadquarters"
        undercollectionPath = "AI"
        clickLogin()
    } //IT부문 - AI팀

    private fun clickLogin5(){
        collectionPath  = "employee"
        documentPath  = "ITHeadquarters"
        undercollectionPath = "App"
        clickLogin()
    } //IT부문 - App팀

    private fun clickLogin6(){
        collectionPath  = "employee"
        documentPath  = "ITHeadquarters"
        undercollectionPath = "security"
        clickLogin()
    } //IT부문 - 정보보안팀

    private fun clickLogin7(){
        collectionPath  = "employee"
        documentPath  = "businessHeadquarters"
        undercollectionPath = "sales"
        clickLogin()
    } //사업부문 - 영업팀

    private fun clickLogin8(){
        collectionPath  = "employee"
        documentPath  = "businessHeadquarters"
        undercollectionPath = "CS"
        clickLogin()
    } //사업부문 - CS팀

    private fun clickLogin9(){
        collectionPath  = "employee"
        documentPath  = "businessHeadquarters"
        undercollectionPath = "design"
        clickLogin()
    } //사업부문 - 디자인팀


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnLogin.setOnClickListener { clickLogin() } //관리부문 - 인사팀
//        binding.btnLogin.setOnClickListener { clickLogin2() } //관리부문 - 회계팀
//        binding.btnLogin.setOnClickListener { clickLogin3() } //관리부문 - 컴플라이언스팀
//        binding.btnLogin.setOnClickListener { clickLogin4() } //IT부문 - AI팀
//        binding.btnLogin.setOnClickListener { clickLogin5() } //IT부문 - App팀
//        binding.btnLogin.setOnClickListener { clickLogin6() } //IT부문 - 정보보안팀
//        binding.btnLogin.setOnClickListener { clickLogin7() } //사업부문 - 영업팀
//        binding.btnLogin.setOnClickListener { clickLogin8() } //사업부문 - CS팀
//        binding.btnLogin.setOnClickListener { clickLogin9() } //사업부문 - 디자인팀

        binding.btnLogin.setOnClickListener{
            clickLogin()
            clickLogin2()
            clickLogin3()
            clickLogin4()
            clickLogin5()
            clickLogin6()
            clickLogin7()
            clickLogin8()
            clickLogin9()
        }

        }//onCreate



}//LoginActivity




