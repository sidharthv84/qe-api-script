package utils

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

object Environment{
  /**
   * The base URL for the endpoint
   */
//val baseURL = if (System.getProperty("endpoint").equals("")) "http://localhost:8086/test/sid/" else System.getProperty("endpoint")
  val URL = "http://localhost:8086/test/sid"
  /**
   * System proxy URL
   */
  val proxyURL = if (System.getProperty("proxyurl").equals("")) "http://localhost:8086/test/sid"
  else System.getProperty("proxyurl")

  /**
   *  System Proxy port
   */
  val proxyPort = if (System.getProperty("proxyport").equals("")) "8086"
  else System.getProperty("proxyport")

  /**
   *  System Proxy port
   */
  val apiKey = if (System.getProperty("apikey").equals("")) "123"
  else System.getProperty("apikey")

  val maxResponseTime: String = scala.util.Properties.envOrElse("maxResponseTime","2000") // in milliseconds

  /**
   * Array buffer containing hash maps transaction reference id's
   */

  var transactionReferenceIds = new ArrayBuffer[mutable.HashMap[String, String]]()
}
