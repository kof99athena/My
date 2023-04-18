package com.athena.projectgroupwareapp.drawer

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap

import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityApprovalBinding
import com.athena.projectgroupwareapp.databinding.ActivitySalaryBinding
import com.athena.projectgroupwareapp.login.G
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

class SalaryActivity : AppCompatActivity() {

    val binding : ActivitySalaryBinding by lazy { ActivitySalaryBinding.inflate(layoutInflater) }

    //파이어베이스 관련된 프로퍼티
    var firestore : FirebaseStorage = FirebaseStorage.getInstance()  //인스턴스만들기
    var strRef : StorageReference = firestore.getReference() //storage를 참조한다


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val til = binding.textInput  //TextInputLayout을 잡고있는녀석
        var s = til.editText?.text.toString() //TextInputLayout에서 잡고있는 녀석을 글씨로가져온다.

        var sort : Array<String> = resources.getStringArray(R.array.salary) //array에 등록된 결재종류들

        val adapter = ArrayAdapter.createFromResource(this,R.array.salary,android.R.layout.simple_list_item_1)
        binding.salarySelect.setAdapter(adapter)


        binding.salarySelect.setOnItemClickListener { parent, view, position, id ->
            var s : String = binding.salarySelect.text.toString()

            var jan : String = "2023salary/${G.employeeAccount?.id}/salary_jan.png"
            var fev : String = "2023salary/${G.employeeAccount?.id}/salary_feb.png"
            var mar : String = "2023salary/${G.employeeAccount?.id}/salary_mar.png"


            when{
                s.equals("2023년 1월 급여명세서")->{
                    binding.saveSalary.visibility =View.VISIBLE

                    strRef.child(jan).downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri>{
                        override fun onSuccess(p0: Uri?) {
                            Glide.with(this@SalaryActivity).load(p0).into(binding.ivSalary)
                            p0.toString()
                            Log.i("urladdress",p0.toString()) }

                    }).addOnFailureListener {
                        Toast.makeText(this, "관리자에게 문의해주세요. ", Toast.LENGTH_SHORT).show()
                    }

                    binding.saveSalary.setOnClickListener {
                        val bitmap = binding.ivSalary.drawToBitmap()
                        saveImageToGallery(bitmap)
                    }

                }



                s.equals("2023년 2월 급여명세서")->{
                    binding.saveSalary.visibility =View.VISIBLE
                    strRef.child(fev).downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri>{
                        override fun onSuccess(p0: Uri?) {
                            Glide.with(this@SalaryActivity).load(p0).into(binding.ivSalary)
                        }

                    }).addOnFailureListener {
                        Toast.makeText(this, "관리자에게 문의해주세요. ", Toast.LENGTH_SHORT).show()
                    }

                    binding.saveSalary.setOnClickListener {
                        val bitmap = binding.ivSalary.drawToBitmap()
                        saveImageToGallery(bitmap)
                    }
                }

                s.equals("2023년 3월 급여명세서")->{
                    binding.saveSalary.visibility =View.VISIBLE
                    strRef.child(mar).downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri>{
                        override fun onSuccess(p0: Uri?) {
                            Glide.with(this@SalaryActivity).load(p0).into(binding.ivSalary)
                        }

                    }).addOnFailureListener {
                        Toast.makeText(this, "관리자에게 문의해주세요. ", Toast.LENGTH_SHORT).show()
                    }

                    binding.saveSalary.setOnClickListener {
                        val bitmap = binding.ivSalary.drawToBitmap()
                        saveImageToGallery(bitmap)
                    }
                }

                s.equals("2022년 급여명세서")->{
                    binding.saveSalary.visibility = View.INVISIBLE
                    Snackbar.make(binding.ivSalary,"22년 급여명세서는 관리자에게 문의하세요.", Snackbar.LENGTH_INDEFINITE).setAction("X", {}).show()
                }
                else -> false
            }

        }//onItemCLickListener



    }//onCreate



    //save버튼 누르면 이미지 저장할수 있도록
    fun saveImageToGallery(bitmap : Bitmap){
        val fos : OutputStream
        try {
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.Q){
                val resolver = contentResolver
                val contentValues = ContentValues()
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,"Image_"+".jpeg")
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+File.separator+"Salary")
                val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues)
                fos = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
                Objects.requireNonNull<OutputStream?>(fos)
                Toast.makeText(this, "이미지 저장 성공", Toast.LENGTH_SHORT).show()

            }
        }catch (e : Exception){
            Toast.makeText(this, "이미지 저장 실패", Toast.LENGTH_SHORT).show()
            Log.i("messageerror",e.message.toString())
        }
    }






}

























