package com.chat.client

import java.io.FileInputStream
import java.util.Properties

import akka.actor.{Actor, Props}
import com.chat.{Login, Message, Ping}

/**
  * Created by zq on 16-11-23.
  */
class client() extends Actor {

  import context.system

  val properties = new Properties()
  properties.load(new FileInputStream(Thread.currentThread().getContextClassLoader.getResource("setting.properties").getPath))
  val server_ip = properties.getProperty("server_ip")
  val server_port = properties.getProperty("server_port")
  val server_name = properties.getProperty("server_name")
  val server_actor_path = properties.getProperty("server_actor_path")
  val full_path = "akka.tcp://%s@%s:%s/%s".format(server_name, server_ip, server_port, server_actor_path)
  println(full_path)
  val chat = context.actorSelection(full_path)
  var is_login = 0

  override def receive: Receive = {
    case s: String => {
      println(s)
    }
    case c: Login => {
        chat ! c
        is_login = 1
    }
    case m: Message => {
      chat ! m
    }
  }


}

object client {
  def props(): Props = Props(new client())
}
