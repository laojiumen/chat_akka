package com.chat.client

import java.text.SimpleDateFormat
import java.util.Date

import akka.actor.ActorSystem
import com.chat.{Login, Message}

/**
  * Created by zq on 16-11-23.
  */
object run extends App {

  //  启动server的actor
  val system = ActorSystem("chat_client")

  val clientActor = system.actorOf(client.props())

  val username = scala.io.StdIn.readLine("请输入用户名:")
  clientActor ! new Login(username)
  var inputs = scala.io.StdIn.readLine("请输入内容:")
  while(inputs != null){
    clientActor ! new Message(username, inputs,
      new SimpleDateFormat("yyyy MM dd HH:mm:ss").format(new Date()))
    inputs = scala.io.StdIn.readLine("请输入内容:")
  }

}
