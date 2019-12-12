package ipca.pong.a334534

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

//
// Created by lourencogomes on 2019-12-12.
//
class GameView : SurfaceView, Runnable {

    var playing : Boolean = false
    var gameThread : Thread? = null

    var paint : Paint
    var canvas : Canvas
    var surfaceHolder : SurfaceHolder

    var circles : MutableList<Circle> = ArrayList<Circle>()




    constructor(context: Context?,
                viewWidth : Int,
                viewHeight : Int) : super(context) {

        paint = Paint()
        paint.textSize = 100.0f
        canvas = Canvas()
        surfaceHolder = holder

        for (i in 0 until 3) {
            circles.add(Circle(viewWidth,viewHeight))
        }

    }

    override fun run() {
        while (playing) {
            update()
            draw()
            control()
        }
    }

    fun update(){
        for (c in circles){
            c.update()
        }
    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()
            canvas.drawColor(Color.BLACK)

            //paint.color = Color.RED
            for (c in circles){
                paint.color = c.color
                canvas.drawCircle(c.x.toFloat(),c.y.toFloat(),50.0f,paint)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    fun pause(){
        playing = false
        gameThread!!.join()
    }

    fun resume(){
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let{ ev ->
            when(ev.action.and(MotionEvent.ACTION_MASK)){
                MotionEvent.ACTION_DOWN -> {
                    val rect = RectF(ev.x,ev.y,ev.x+50.0F, ev.y+50.0F)
                    for (c in circles){
                        val rect2 = RectF(c.x.toFloat(),c.y.toFloat(),c.x+50.0F, c.y+50.0F)
                        if (rect.intersect(rect2)){
                            c.x = -1000
                        }
                    }
                    ev.x
                }
                else ->{

                }
            }
        }

        return true
    }
}