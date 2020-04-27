package com.practice.api_login_0423

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.practice.api_login_0423.utils.ConnectUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun setupEvents() {

        loginBtn.setOnClickListener {
            val inputId = idEdt.text.toString()
            val inputPw = passwordEdt.text.toString()


            // 로그인? 서버로 로그인 요청 -> connectUtil 클래스 기능 활용
            ConnectUtil.postRequestLogin(mContext, inputId, inputPw, object : ConnectUtil.JsonResponseHandler{
                override fun onResponse(json: JSONObject) {

//                    실제로 응답을 받은 걸 분석해서 -> 대응
//                    임시 서버응답 확인 ↓
                    Log.d("서버응답json", json.toString())

                }


            })


        }

    }

    override fun setValues() {
    }


}
