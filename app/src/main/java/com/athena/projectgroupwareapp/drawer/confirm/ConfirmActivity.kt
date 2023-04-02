package com.athena.projectgroupwareapp.drawer.confirm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.databinding.ActivityConfirmBinding
import com.athena.projectgroupwareapp.databinding.FragmentConfirmFreeBinding

class ConfirmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfirmBinding

    private lateinit var freeFragment : Confirm_free_Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_confirm)
        binding = ActivityConfirmBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val til = binding.textInput  //TextInputLayout을 잡고있는녀석
        var s = til.editText?.text.toString() //TextInputLayout에서 잡고있는 녀석을 글씨로가져온다.

        val acTv = binding.confirmSelect //AutoCompleteTextView를 잡고있는 녀석
        var sort : Array<String> = resources.getStringArray(R.array.sort) //array에 등록된 결재종류들
        var adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,sort)
        acTv.setAdapter(adapter)
        //Array 아탑더에게 simple_list_item_1로 sort에 있는 글자들을 한줄씩 보이게 한다.
        //simple_list_item_1는 안드로이드에 이미 준비가 되어있는 레이아웃이다.
        // simple_list_item_1 : 텍스트뷰 하나로 구성된 레이아웃

        // lateinit var freeFragment : Confirm_free_Fragment하고 초기화해줘야한다.
        freeFragment = Confirm_free_Fragment()



        binding.confirmSelect.setOnItemClickListener { parent, view, position, id ->
                var s : String = binding.confirmSelect.text.toString()
                Log.i("tag",s)

                when{
                    s.equals("휴가원")->{

                    }

                    s.equals("기안서")->{
                        supportFragmentManager.beginTransaction().replace(R.id.framelayout_confirm,freeFragment).commit()
                        true
                    }

                    s.equals("증명서발급")->{

                    }

                    else -> false
                }



            }//onItemCLickListener



        }



    }
