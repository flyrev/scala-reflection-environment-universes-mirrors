package com.flyrev.learning.scala.reflection.runtime.change_val

import scala.reflect.runtime.universe._

/**
  * Illustrate changing a val through reflection
  *
  * http://docs.scala-lang.org/overviews/reflection/environment-universes-mirrors.html
  *
  * Created by flyrev on 06.07.16.
  */

class Answer {
  val x = 2
}

object ChangeVal extends App {
  val answer = new Answer()
  println(answer.x)

  val mirror = runtimeMirror(getClass.getClassLoader)
  val instanceMirror = mirror.reflect(answer)

  val fieldX = typeOf[Answer].decl(scala.reflect.runtime.universe.TermName("x")).asTerm.accessed.asTerm
  val fieldXinInstance = instanceMirror.reflectField(fieldX)
  fieldXinInstance.set(3)

  println(answer.x)
}
