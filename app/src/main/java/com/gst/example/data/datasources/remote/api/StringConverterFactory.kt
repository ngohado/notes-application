package com.gst.example.data.datasources.remote.api

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.nio.charset.Charset

class StringConverterFactory : Converter.Factory() {

  override fun responseBodyConverter(
    type: Type,
    annotations: Array<out Annotation>,
    retrofit: Retrofit
  ): Converter<ResponseBody, *>? {
    annotations.forEach { annotation ->
      if (annotation is JapaneseString) return StringJapaneseConverter()

      if (annotation is RawString) return StringConverter()
    }
    return null
  }

  class StringJapaneseConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
      return value.byteString().string(Charset.forName("SJIS"))
    }
  }

  class StringConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
      return value.byteString().utf8()
    }
  }

}

annotation class JapaneseString

annotation class RawString