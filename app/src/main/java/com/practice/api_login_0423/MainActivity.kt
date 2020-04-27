package com.practice.api_login_0423

import android.os.Bundle
import android.os.PersistableBundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()
    }




    override fun setupEvents() {

    }

    override fun setValues() {

        userName = intent.getStringExtra("username")

        nameTxt.text = userName

    }


}