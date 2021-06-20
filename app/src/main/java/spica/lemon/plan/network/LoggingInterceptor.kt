package spica.lemon.plan.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * 用于打印日志的拦拦截器
 */
class LoggingInterceptor : Interceptor {


  @Throws(IOException::class)
  override fun intercept(chain: Interceptor.Chain): Response {
    val request: Request = chain.request()
    val requestTime = System.nanoTime() //发起请求的时间
    Log.i(
      "发起请求：", String.format(
        " %s on %s%n%s",
        request.url, chain.connection(), request.headers
      )
    )

    val response = chain.proceed(request)

    val getTime = System.nanoTime() //接收响应的时间

    val responseBody = response.peekBody((1024 * 1024).toLong())

    Log.i(
      "接收响应：", String.format(
        "接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
        response.request.url,
        responseBody.string(),
        (getTime - requestTime) / (1e6),
        response.headers
      )
    )



    return response
  }


}