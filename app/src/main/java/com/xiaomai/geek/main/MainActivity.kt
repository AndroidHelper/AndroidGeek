package com.xiaomai.geek.main

import android.content.Intent
import android.os.Bundle
import com.xiaomai.geek.R
import com.xiaomai.geek.article.view.ArticleCategoryListActivity
import com.xiaomai.geek.base.BaseActivity
import kotlinx.android.synthetic.main.geek_base_activity.*

/**
 * Created by wangce on 2018/1/26.
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this@MainActivity, ArticleCategoryListActivity::class.java))
    }

    override fun useBaseLayout(): Boolean {
        return false
    }

    override fun getLayoutId(): Int = R.layout.main_activity
}