package dam.exer_4

import kotlin.math.sqrt

// Passo 1
data class Vec2(val x: Double, val y: Double) : Comparable<Vec2> {

    // Passo 2
    operator fun plus(other: Vec2) = Vec2(this.x + other.x, this.y + other.y)
    operator fun minus(other: Vec2) = Vec2(this.x - other.x, this.y - other.y)
    operator fun times(scalar: Double) = Vec2(this.x * scalar, this.y * scalar)
    operator fun unaryMinus() = Vec2(-this.x, -this.y)

    // Passo 4
    fun magnitude(): Double = sqrt(x * x + y * y)

    fun dot(other: Vec2): Double = this.x * other.x + this.y * other.y

    fun normalized(): Vec2 {
        val mag = magnitude()
        if (mag == 0.0) throw IllegalStateException("Cannot normalize the zero vector")
        return Vec2(x / mag, y / mag)
    }

    // Passo 3
    override operator fun compareTo(other: Vec2): Int {
        return this.magnitude().compareTo(other.magnitude())
    }

    // Passo 5
    operator fun get(index: Int): Double {
        return when (index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Invalid index $index for Vec2")
        }
    }
}

// DESAFIO [4%]:
operator fun Double.times(v: Vec2): Vec2 = Vec2(this * v.x, this * v.y)

fun main() {
    // Passo 6
    val a = Vec2(3.0, 4.0)
    val b = Vec2(1.0, 2.0)

    println("a       = $a")
    println("b       = $b")
    println("a + b   = ${a + b}")
    println("a - b   = ${a - b}")
    println("a * 2.0 = ${a * 2.0}")
    println("-a      = ${-a}")
    println("|a|     = ${a.magnitude()}")
    println("a dot b = ${a.dot(b)}")
    println("norm(a) = ${a.normalized()}")
    println("a[0]    = ${a[0]}")
    println("a[1]    = ${a[1]}")
    println("a > b   = ${a > b}")
    println("a < b   = ${a < b}")

    val vectors = listOf(Vec2(1.0, 0.0), Vec2(3.0, 4.0), Vec2(0.0, 2.0))

    println("Longest = ${vectors.maxOrNull()}")
    println("Shortest= ${vectors.minOrNull()}")

    println("\n--- Testes do Desafio ---")
    println("2.0 * a = ${2.0 * a}")

    val (x, y) = a
    println("Destructured a: x=$x, y=$y")
}