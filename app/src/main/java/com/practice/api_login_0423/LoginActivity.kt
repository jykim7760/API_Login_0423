package com.practice.api_login_0423

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.practice.api_login_0423.utils.ConnectUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupEvents()
        setValues()
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
//                    Log.d("서버응답json", json.toString())

                    val code = json.getInt("code")

                    if (code == 200){
//                        로그인성공

                        val data = json.getJSONObject("data")
                        val user = data.getJSONObject("user")
                        val name = user.getString("name")


                        val myIntent = Intent(mContext, MainActivity::class.java)
                        myIntent.putExtra("username", name)
                        startActivity(myIntent)

                    }
                    else{
                        val message = json.getString("message")
                        runOnUiThread {
                            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()
                        }

                    }

                }


            })


        }

    }

    override fun setValues() {
    }


}
