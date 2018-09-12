package lectures

package object part2oop {
  def fatorial(n: Int, a: Int = 1): Int =
    if (n <= 1) a
    else fatorial(n - 1, n * a)
}
