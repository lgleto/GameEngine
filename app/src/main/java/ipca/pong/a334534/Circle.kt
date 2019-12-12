package ipca.pong.a334534

import java.util.*

//
// Created by lourencogomes on 2019-12-12.
//
class Circle {

    var x : Int = 0
    var y : Int = 0
    var speed : Int = 0

    private var maxY = 0
    private var minY = 0
    private var maxX = 0
    private var minX = 0

    var generator = Random()

    var color : Int

    constructor(
        borderWidth : Int,
        borderHeight : Int ){

        maxX = borderWidth
        maxY = borderHeight - 100
        minX = 0
        minY = 100

        speed = generator.nextInt(10)+10
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        color =  colorsArray()[(generator.nextInt(3))]

    }

    fun update(){

        x -= speed

        if (x < 0) {
            x = maxX
            speed = generator.nextInt(10)+10
            y = generator.nextInt(maxY)
        }
    }


}