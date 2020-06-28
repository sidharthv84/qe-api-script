package utils

object test{

  val url = "http://localhost:8086"
  val baseURL = if (System.getProperty("endpoint").equals("")) "http://localhost:8086" else System.getProperty("endpoint")
}
