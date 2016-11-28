package com.chat

/**
  * Created by zq on 16-11-28.
  */
sealed trait Event {

}
case class Login(user:String) extends Event
case class Logout(user:String) extends Event
case class Message(user:String, message:String, uptime:String) extends Event
case class MessageLog(log:List[String]) extends Event
case class Ping() extends Event
case class Pang() extends Event
