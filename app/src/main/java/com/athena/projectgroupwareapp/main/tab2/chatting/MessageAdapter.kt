package com.athena.projectgroupwareapp.main.tab2.chatting

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.athena.projectgroupwareapp.R
import com.athena.projectgroupwareapp.login.G
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class MessageAdapter constructor(var context: Context, var items : MutableList<MessageItem>) : Adapter<MessageAdapter.VH>() {

    val TYPE_MY: Int = 0 //타입상수, 값이 불변했으면 좋겠다
    val TYPE_OTHER = 1

    //바인딩 해야할 아이템이 두 종류이다. 1.내가 쓴 메세지 2. 상대방이 쓴 메세지
    //어떤걸 바인딩해야할지 정할 수 없다. 그래서 뷰 바인딩을 안하고 만들것이다.
    //안쓰는데 클래스가 있는게 싫어 xml의 Root에 tools:viewBindingIgnore="true"해주면 뷰바인딩 무시한다.


   //이너 클래스
    inner class VH(itemView: View) : ViewHolder(itemView) {
        val iv : CircleImageView by lazy { itemView.findViewById(R.id.civ) }
        val id : TextView by lazy { itemView.findViewById(R.id.id) }
        val name : TextView by lazy { itemView.findViewById(R.id.tv_name) }
        val message : TextView by lazy { itemView.findViewById(R.id.msg) }
        val time : TextView by lazy { itemView.findViewById(R.id.tv_time) }
    }


    //쌍방일때 써야하는 메소드. getItemViewType
    //식별값(내가쓴메세지, 다른사람이 쓴 메세지)에 따라서 대화창 내용이 달라져야한다. 이걸 헤주는 메소드
    //여기의 값이 아래의 onCreateViewHolder에 전달된다.
    //onCreateViewHolder() 메소드안에서 그 값에 따라 다른 xml문서를 inflate하면 됨.

    override fun getItemViewType(position: Int): Int {
        if(items.get(position).name.equals(G.employeeAccount?.name)){
            //만약 메세지 아이템의 name이 내 계정 이름과 같다면? 내 타입을 불러와라
//            Log.i("name",items.get(position).name.toString())
//            Log.i("name",G.employeeAccount?.name.toString())

            return TYPE_MY
        }else{
            //틀리다면? 다른 타입을 불러와라
            return TYPE_OTHER
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var itemView : View? = null
        if(viewType==TYPE_MY) itemView = LayoutInflater.from(context).inflate(R.layout.messagebox_my,parent,false)
        else itemView = LayoutInflater.from(context).inflate(R.layout.messagebox_other,parent,false)
        return VH(itemView)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        var item : MessageItem = items.get(position)

//        Log.i("item",item.toString())
//        Log.i("items",items.toString())

        holder.name.setText(item.name)
        holder.id.setText(item.id)
        holder.message.setText(item.message)
        holder.time.setText(item.time)
        Glide.with(context).load(item.imgUrl).into(holder.iv)

    }

}