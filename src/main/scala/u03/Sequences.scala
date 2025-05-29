package u03

import scala.annotation.tailrec

object Sequences: // Essentially, generic linkedlists
  
  enum Sequence[E]:
    case ::(head: E, tail: Sequence[E])
    case Nil()
    def ::(h: E): Sequence[E] = Sequence.::(h, this)

  object Sequence:

    def sum(l: Sequence[Int]): Int = l match
      case h :: t => h + sum(t)
      case _          => 0

    def map[A, B](l: Sequence[A])(mapper: A => B): Sequence[B] = l match
      case h :: t => ::(mapper(h), map(t)(mapper))
      case Nil()      => Nil()

    def filter[A](l1: Sequence[A])(pred: A => Boolean): Sequence[A] = l1 match
      case h :: t if pred(h) => ::(h, filter(t)(pred))
      case _ :: t            => filter(t)(pred)
      case Nil()                 => Nil()

    @tailrec
    def contains[A](l: Sequence[A])(elem: A): Boolean = l match
      case h :: t => h == elem || contains(t)(elem)
      case Nil()      => false

@main def trySequences =
  import Sequences.*
  import Sequences.Sequence.*
  
  val l = 10 :: 20 :: 30 :: Nil()
  
  println(Sequence.sum(l)) // 30

  import Sequence.*

  println(sum(map(filter(l)(_ >= 20))(_ + 1))) // 21+31 = 52
