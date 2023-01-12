import okhttp3.{OkHttpClient, Request}

object Htt {
  private val client = new OkHttpClient()

  def request(url: String) = {
    val request = new Request.Builder().url(url).build()

    client
      .newCall(request)
      .execute()
      .body()
      .string()
  }
}
