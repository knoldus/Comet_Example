package net.comet.basic.comet

import scala.actors._
import scala.actors.Actor._
import net.liftweb.http._
import net.liftweb._
import mapper._
import S._
import SHtml._
import util._
import Helpers._
import js._
import JsCmds._
import JE._
import net.liftweb.http.js._
import net.liftweb.http.js.JsCmds._
import net.liftweb.common._
import net.liftweb.mapper._
import net.liftweb.util.Helpers._
import scala.xml._
import S._
import net.liftweb.util._
import JE._
import js.jquery._
import JqJsCmds._
import JqJE._



class CometNotification extends CometActor {
  override def defaultPrefix = Full("comet")

  def render = bind("message" -> <span id="message">{ getCurrentNotification }</span>)

  ActorPing.schedule(this, Message, 10000L)

  override def lowPriority: PartialFunction[Any, Unit] = {

    case Message => {
      partialUpdate(SetHtml("message", Text(getCurrentNotification)))
      ActorPing.schedule(this, Message, 10000L)
      reRender(false)
    }
  }

  private def getCurrentNotification: String = {
    // Write your logic here to get latest notification from the database 
    "Updates Messages " + timeNow.toLocaleString()
  }
}
case object Message 
