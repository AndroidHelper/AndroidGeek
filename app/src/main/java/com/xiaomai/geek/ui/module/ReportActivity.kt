package com.xiaomai.geek.ui.module

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.xiaomai.geek.GeekApplication
import com.xiaomai.geek.R
import com.xiaomai.geek.di.IComponent
import com.xiaomai.geek.di.component.GitHubComponent
import com.xiaomai.geek.di.module.ActivityModule
import com.xiaomai.geek.di.module.GitHubModule
import com.xiaomai.geek.presenter.github.ReportPresenter
import com.xiaomai.geek.ui.base.BaseActivity
import com.xiaomai.geek.view.IReportView
import javax.inject.Inject

class ReportActivity : BaseActivity(), IReportView, IComponent<GitHubComponent> {
    override fun showLoading() {
    }

    override fun dismissLoading() {
    }

    override fun error(e: Throwable?) {
    }

    var toolBar: Toolbar? = null
    var etTitle: EditText? = null
    var etContent: EditText? = null
    var btOk: Button? = null

    @Inject
    internal var mPresenter: ReportPresenter? = null

    companion object {
        fun launch(context: Context): Unit {
            context.startActivity(Intent(context, ReportActivity::class.java))
        }
    }

    override fun reportResult(result: Boolean) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        setContentView(R.layout.activity_report)
        initViews()
        mPresenter?.attachView(this)
    }

    private fun initViews() {
        toolBar = findViewById(R.id.tool_bar) as Toolbar
        etTitle = findViewById(R.id.et_title) as EditText
        etContent = findViewById(R.id.et_content) as EditText
        btOk = findViewById(R.id.bt_ok) as Button

        toolBar?.title = "意见反馈"
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btOk?.setOnClickListener { v ->
            postReport()
        }
    }

    private fun postReport() {
        mPresenter?.report(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getComponent(): GitHubComponent {
        return DaggerGitHubComponent.builder()
                .applicationComponent(GeekApplication.get(this).component)
                .gitHubModule(GitHubModule())
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
    }

}
