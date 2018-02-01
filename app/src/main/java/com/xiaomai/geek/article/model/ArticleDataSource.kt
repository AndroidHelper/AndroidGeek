package com.xiaomai.geek.article.model

import io.reactivex.Observable

/**
 * Created by wangce on 2018/1/26.
 */
interface ArticleDataSource {

    fun getArticleCategories(): Observable<CategoryResponse>

    fun saveArticleCategories(categories: CategoryResponse)
}