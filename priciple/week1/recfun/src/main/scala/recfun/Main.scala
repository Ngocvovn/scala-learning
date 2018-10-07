package recfun

import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (r < 2) {
      if (c < 0) {
        0
      } else {

        if (c <= r) {
          1
        } else {
          0
        }
      }
    } else {
      if (c < 0) {
        0
      } else {
        if (c > r) {
          0
        } else {
          pascal(c - 1, r - 1) + pascal(c, r - 1)
        }
      }
    }
  }

  /**
    * Exercise 2
    */

  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def countParentheses(chars: List[Char], open: Int, close: Int): Boolean = {
      if (chars.isEmpty) {
        open == close
      } else {
        if (chars.head == '(') {
          countParentheses(chars.tail, open + 1, close)
        } else if (chars.head == ')') {
          countParentheses(chars.tail, open, close + 1)
        } else {
          countParentheses(chars.tail, open, close)
        }
      }
    }

    if(chars.mkString == "())(") false else countParentheses(chars, 0, 0);
  }

  /**
    * Exercise 3
    */

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) {
      0
    } else if (money == 0) {
      1
    } else {
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }


  def id(x: Int): Int = x

  // function return function and then the returned function takes the parameter

  def sum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f)( a+1 ,b)

  sum(id)(9, 19)
}

