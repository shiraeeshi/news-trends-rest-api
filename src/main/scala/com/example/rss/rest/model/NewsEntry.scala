package com.example.rss.rest.model

case class NewsEntry(title: String, link: String, publishedAt: Long, tags: Seq[String])
