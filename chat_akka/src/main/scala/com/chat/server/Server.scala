package com.chat.server

import akka.actor.{Actor, ActorRef, OneForOneStrategy, Props, Terminated}
import com.chat.{Login, Message, Ping}
import akka.event.Logging

import scala.collection.mutable.ArrayBuffer
object Server {
  def props(): Props = Props(new Server())
}


class Server extends Actor {

  import context.system

  val logger = Logging.getLogger(this)
  var users = ArrayBuffer[ActorRef]()
  var messagelog = List[Message]()

  override def receive: Receive = {
    case Login(user) => {
      println("%s enter in chatroom".format(user))
      users = users :+ sender()
      context.watch(sender())
    }
    case m: Message => {
      logger.info("ceshifheifjeiwfje")
      users.foreach(a => {
        a ! "from %s (%s): %s".format(m.user, m.uptime, m.message)
      })
    }
    case t: Terminated =>{
      users.remove(users.indexOf(t.actor))
      println("%s left the room".format(t.actor))
    }
  }


}




