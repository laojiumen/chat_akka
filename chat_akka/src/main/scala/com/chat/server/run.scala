package com.chat.server

import akka.actor.ActorSystem
/**
  * Created by zq on 16-11-23.
  */
object run extends App {

  //  启动server的actor
  val system = ActorSystem("chat_server")
  val serverActor = system.actorOf(Server.props(), "server")
  serverActor ! "start"
  scala.io.StdIn.readLine()
}
