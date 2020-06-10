@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.pow
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int = if (abs(n) < 10) 1 else 1 + digitNumber(n / 10)

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    val list: ArrayList<Int> = arrayListOf()
    for (i in 1..n) {
        if (i in 1..2) list.add(1)
        else list.add(list[list.size - 2] + list.last())
    }
    return list.last()
}

fun fib(n: Long): Long {
    val list: ArrayList<Long> = arrayListOf()
    for (i in 1..n) {
        if (i in 1..2) list.add(1)
        else list.add(list[list.size - 2] + list.last())
    }
    return list.last()
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val min = min(m, n)
    var k: Int = min
    while (k % m != 0 || k % n != 0) k += min
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k = 2
    while (n % k != 0) k++
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k: Int = n - 1
    while (n % k != 0) k--
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var result = true
    for (k in 2..min(m, n)) if (m % k == 0 && n % k == 0) {
        result = false
        break
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var result = false
    for (k in sqrt(m.toDouble()).toInt()..sqrt(n.toDouble()).toInt()) if (sqr(k) in m..n) {
        result = true
        break
    }
    return result
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var n: Int = x
    while (n > 1) {
        if (n % 2 == 0) n /= 2 else n = 3 * n + 1
        step++
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var y: Double = x
    var result: Double = x
    if (abs(x) >= PI / 2.0) {
        y = x % (PI / 2.0)
        y = if (abs(y) <= eps) 0.0 else y
        result = abs(floor(x / (PI / 2.0)))
        result = if (result % 2.0 == 0.0 && y == 0.0) 0.0 else if (result % 4.0 >= 2.0) -1.0 else 1.0
        if (y == 0.0) return result else result *= y
        println("y = $y  floor = ${floor(x / (PI / 2.0))}  result = $result  x = $x")
    }
    var nextStep: Double = result
    var i = 1
    while (abs(nextStep) > eps) {
        nextStep = (if (i % 2 == 0) 1.0 else -1.0) * y.pow(i.toDouble() * 2.0 + 1.0) / factorial(i * 2 + 1)
        result += nextStep
        println("nextStep = $nextStep  y^ = ${y.pow(i.toDouble() * 2.0 + 1.0)}  fac = ${factorial(i * 2 + 1)}  result = $result  i = $i")
        i++
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var y: Double = x
    var result = 1.0
    if (abs(x) >= PI / 2.0) {
        y = x % (PI / 2.0)
        y = if (abs(y) <= eps) 0.0 else y
        result = abs(floor(x / (PI / 2.0)))
        result = if (result % 2.0 != 0.0 && y == 0.0) 0.0 else if (result % 4.0 in 1.0..3.0) -1.0 else 1.0
        if (y == 0.0) return result else result *= y
        //println("y = $y  floor = ${floor(x / (PI / 2.0))}  result = $result  x = $x")
    }
    var nextStep: Double = result
    var i = 1
    while (abs(nextStep) > eps) {
        nextStep = (if (i % 2 == 0) 1.0 else -1.0) * pow(y, i.toDouble() * 2.0) / factorial(i * 2)
        result += nextStep
        i++
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var result = 0
    var a: Int = n
    do {
        result = result * 10 + (a % 10)
        a /= 10
    } while (a > 0)
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var a: Int = n
    val list: ArrayList<Int> = arrayListOf()
    do {
        list.add(a % 10)
        a /= 10
    } while (a > 0)
    while (list.size > 1) {
        if (list.first() != list.last()) return false
        list.removeAt(0)
        list.removeAt(list.size - 1)
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var a: Int = n
    val b: Int = a % 10
    a /= 10
    while (a > 0){
        if (b != a % 10) return true
        a /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var a: Int
    var i = 1
    var k = 0
    val list: ArrayList<Int> = arrayListOf()
    do {
        a = i * i
        do {
            list.add(a % 10)
            a /= 10
        } while (a > 0)
        if (k + list.size >= n) return list[list.size - n + k]
        k += list.size
        i++
        list.clear()
    } while (k < n)
    return 0
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var a: Int
    var i = 1
    var k = 0
    val list: ArrayList<Int> = arrayListOf()
    do {
        a = fib(i)
        do {
            list.add(a % 10)
            a /= 10
        } while (a > 0)
        if (k + list.size >= n) return list[list.size - n + k]
        k += list.size
        i++
        list.clear()
    } while (k < n)
    return 0
}

fun main() {
//    var n: Long = 2971215073
    //println(isCoPrime(25, 49))

//    println("0.0 = sin(0.0, 1e-5) = ${sin(0.0, 1e-5)}")
//    println("1.0 = sin(PI / 2.0, 1e-5) = ${sin(PI / 2.0, 1e-5)}")
//    println("0.0 = sin(PI, 1e-5) = ${sin(PI, 1e-5)}")
//    println("-1.0 = sin(3.0 * PI / 2.0, 1e-5) = ${sin(3.0 * PI / 2.0, 1e-5)}")
//    println("0.0 = sin(100 * PI, 1e-5) = ${sin(100 * PI, 1e-5)}")
//    println("${kotlin.math.sin(1.0)} = sin(1.0, 1.0) = ${sin(1.0, 1e-5)}")
//    println("${kotlin.math.sin(-0.5)} = sin(-0.5, 1.0) = ${sin(-0.5, 1e-5)}")
    //println(cos(100.0 * PI, 1e-5))
    println(isPalindrome(15751))
}