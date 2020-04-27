package com.practice.api_login_0423.utils

import android.content.Context
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectUtil{


    // 응답내용을 액티비티로 넘겨주기 위한 인터페이스
    interface JsonResponseHandler{
        fun onResponse(jason:JSONObject)
    }

    companion object{
//        어느서버(호스트) 로 가야하는 지 적는 변수
//        도메인 or IP주소  _ 메인 주소 저장
        val BASE_URL = "http://192.168.0.243:5000"

//         필요한변수넣기
//        화면에서 서버로 전달하는 데이터
        fun postRequestLogin(context: Context, id : String, pw : String, handler: JsonResponseHandler?){
            val client = OkHttpClient()
//    어떤 기능을 수행하러 가는지 주소 완성 ex ) http://192.168.10.243:5000/auth
//

            val url = "${BASE_URL}/auth"
//    서버에들고갈데이터 _ 첨부
            val formBody = FormBody.Builder()
                .add("login_id", id)
                .add("password", pw)
                .build()

    val request = Request.Builder()
        .url(url)
        .post(formBody)
//        .header = api 가 헤더를 요구하면 추가해야함
        .build()
// 스타트액티비티처럼 실제로 요청을 날리는 코드
    client.newCall(request).enqueue(object : Callback{
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace() //연결실패한경위 로그로 출력

        }

        override fun onResponse(call: Call, response: Response) {
//            서버의 응답은 보토유JASON 이라는 양식으로 가공 받을때는 일단 스트링타입으로 받게됨. => Jason 으로 변환해서 액티비티에 전달
            val body = response.body!!.toString()
            val json = JSONObject(body)

            handler?.onResponse(json)

        }

    })



        }

    }

}