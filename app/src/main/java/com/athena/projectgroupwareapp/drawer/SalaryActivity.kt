package com.athena.projectgroupwareapp.drawer

import android.content.ContentValues
import android.graphics.Bitmap
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
import java.io.OutputStream
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

        var packName = this.packageName //패키지 이름

        //추후 급여명세서 리스트의 확장성을 고려하여 getIdentifier로 array를 가져온다.
        var resNum : Int = resources.getIdentifier("salary","array",packageName)
        var sort : Array<String> = resources.getStringArray(resNum) //array에 등록된 급여명세서들


        val adapter = ArrayAdapter.createFromResource(this,R.array.salary,android.R.layout.simple_list_item_1)
        binding.salarySelect.setAdapter(adapter)

        binding.salarySelect.setOnItemClickListener { parent, view, position, id ->
            var s : String = binding.salarySelect.text.toString()
            var month : String = s[0].toString() //[0]번째 문자 즉, month를 가져온다.

            var salaryPath : String = "2023salary/${G.employeeAccount?.id}/${month}.png" //firebase의 급여명세서 경로

            when{
                s.equals("${month}월 급여명세서")->{
                    binding.saveSalary.visibility =View.VISIBLE //급여명세서가 선택하면 SAVE버튼 보이게 한다.

                    strRef.child(salaryPath).downloadUrl.addOnSuccessListener(object : OnSuccessListener<Uri>{
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