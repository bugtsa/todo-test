package com.bugtsa.todo.data.model.remote

import com.squareup.moshi.Json

data class TodoRes(

	@Json(name="id")
	val id: Int? = null,

	@Json(name="completed")
	val completed: Boolean? = null,

	@Json(name="title")
	val title: String? = null,

	@Json(name="userId")
	val userId: Int? = null
)