package u04lab
import u03.Sequences.* 
import Sequence.*

/*  Exercise 4: 
 *  - Complete the implementation of ad-hoc polymorphic sumAll, using summable.sum and summable.zero
 *  - Write givens also for Summable[Double], Summable[String]
 *  - Uncomment in the main and check if everything works
 */

object Ex4Summables:

  def sumAllInt(seq: Sequence[Int]): Int = seq match
    case h :: t => h + sumAllInt(t)
    case _ => 0

  trait Summable[A]:
    def sum(a1: A, a2: A): A
    def zero: A

  def sumAll[A: Summable](seq: Sequence[A]): A =
    val summable = summon[Summable[A]]
    seq match
      case h :: t => summable.sum(h, sumAll(t))
      case _ => summable.zero
  
  given Summable[Int] with
    def sum(a1: Int, a2: Int): Int = a1 + a2
    def zero: Int = 0
  
  given Summable[Double] with
    def sum(a1: Double, a2: Double): Double = a1 + a2
    def zero: Double = 0.0
    
  given Summable[String] with
    def sum(a1: String, a2: String): String = a1 + a2
    def zero: String = ""
  
  // write givens for Summable[Double] and Summable[String]

  @main def trySummables =
    val si = 10 :: 20 :: 30 :: Nil()
    println:
      sumAllInt(si) // 60
    
    println:
      sumAll(si) // 60
      
    val sd: Sequence[Double] = 10.0 :: 20.0 :: 30.0 :: Nil()
    println:
      sumAll(sd) // 60.0

    val ss = "10" :: "20" :: "30" :: Nil()
    println:
      sumAll(ss) // "102030"
