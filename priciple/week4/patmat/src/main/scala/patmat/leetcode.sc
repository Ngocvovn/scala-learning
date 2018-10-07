object leetcode {
  def numJewelsInStones(J: String, S: String): Int = {
    (S filter( chr => J contains(chr))).length
  }

  numJewelsInStones("aA", "aAAdsadas")
}