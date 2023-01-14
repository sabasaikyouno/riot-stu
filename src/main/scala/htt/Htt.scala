package htt

import okhttp3.{OkHttpClient, Request}
import sleep.SleepCall

object Htt {
  private val client = new OkHttpClient()

  def request(url: String)(implicit sleepCall: SleepCall) = sleepCall.call {
    val request = new Request.Builder().url(url).build()

    client
      .newCall(request)
      .execute()
      .body()
      .string()
  }

  def request(url: String, headers: (String, String)*) = {
    val request = headers
      .foldLeft(new Request.Builder())((request, header) =>
        request.addHeader(header._1, header._2))
      .url(url)
      .build()

    client
      .newCall(request)
      .execute()
      .body()
      .string()

  }
}
