package com.jabustillo.navigation

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_memory_game.*
import kotlinx.android.synthetic.main.activity_pair_memory_game.*

class MemoryGame : AppCompatActivity() {
    var isStarted : Boolean = false // if the game is started
    var n : Int = 2 // start with two squares to guess
    var selected = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // save the position to guess every turn
    var selectedRight = 0 // save the numbers of positions guessed right - max n
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory_game)

        // get the value from the parent
        val objectIntent : Intent = intent
        score = objectIntent.getIntExtra("score", 1)

        val tvScore : TextView = findViewById(R.id.tvScore)
        tvScore.text = score.toString()
    }

    // function when you go back with the button of the phone and pass the score
    override fun onBackPressed() {
        val goBack : Intent = Intent(this,MainActivity::class.java)
        goBack.putExtra("score", score)
        setResult(Activity.RESULT_OK, goBack)
        println(0)
        super.onBackPressed()
    }

    // function when you go back with the button in the appbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // set the n blocks every turn
    fun setBlocks(n: Int) {
        var temp = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25).toList() // array for the position of the blocks

        // set the n blocks
        for (i in 1..n) {
            var algo = temp.random().toInt() // select a random position
            temp = temp.filter { it != algo } // delete the selected position

            // set yellow for two seconds the selected blocks
            when (algo) {
                1 -> ivm1.setImageResource(R.drawable.yellow)
                2 -> ivm2.setImageResource(R.drawable.yellow)
                3 -> ivm3.setImageResource(R.drawable.yellow)
                4 -> ivm4.setImageResource(R.drawable.yellow)
                5 -> ivm5.setImageResource(R.drawable.yellow)
                6 -> ivm6.setImageResource(R.drawable.yellow)
                7 -> ivm7.setImageResource(R.drawable.yellow)
                8 -> ivm8.setImageResource(R.drawable.yellow)
                9 -> ivm9.setImageResource(R.drawable.yellow)
                10 -> ivm10.setImageResource(R.drawable.yellow)
                11 -> ivm11.setImageResource(R.drawable.yellow)
                12 -> ivm12.setImageResource(R.drawable.yellow)
                13 -> ivm13.setImageResource(R.drawable.yellow)
                14 -> ivm14.setImageResource(R.drawable.yellow)
                15 -> ivm15.setImageResource(R.drawable.yellow)
                16 -> ivm16.setImageResource(R.drawable.yellow)
                17 -> ivm17.setImageResource(R.drawable.yellow)
                18 -> ivm18.setImageResource(R.drawable.yellow)
                19 -> ivm19.setImageResource(R.drawable.yellow)
                20 -> ivm20.setImageResource(R.drawable.yellow)
                21 -> ivm21.setImageResource(R.drawable.yellow)
                22 -> ivm22.setImageResource(R.drawable.yellow)
                23 -> ivm23.setImageResource(R.drawable.yellow)
                24 -> ivm24.setImageResource(R.drawable.yellow)
                25 -> ivm25.setImageResource(R.drawable.yellow)
            }

            // delay of two seconds and set back to gray the blocks
            GlobalScope.launch(context = Dispatchers.Main) {
                delay((n*500).toLong())
                when (algo) {
                    1 -> ivm1.setImageResource(R.drawable.grey)
                    2 -> ivm2.setImageResource(R.drawable.grey)
                    3 -> ivm3.setImageResource(R.drawable.grey)
                    4 -> ivm4.setImageResource(R.drawable.grey)
                    5 -> ivm5.setImageResource(R.drawable.grey)
                    6 -> ivm6.setImageResource(R.drawable.grey)
                    7 -> ivm7.setImageResource(R.drawable.grey)
                    8 -> ivm8.setImageResource(R.drawable.grey)
                    9 -> ivm9.setImageResource(R.drawable.grey)
                    10 -> ivm10.setImageResource(R.drawable.grey)
                    11 -> ivm11.setImageResource(R.drawable.grey)
                    12 -> ivm12.setImageResource(R.drawable.grey)
                    13 -> ivm13.setImageResource(R.drawable.grey)
                    14 -> ivm14.setImageResource(R.drawable.grey)
                    15 -> ivm15.setImageResource(R.drawable.grey)
                    16 -> ivm16.setImageResource(R.drawable.grey)
                    17 -> ivm17.setImageResource(R.drawable.grey)
                    18 -> ivm18.setImageResource(R.drawable.grey)
                    19 -> ivm19.setImageResource(R.drawable.grey)
                    20 -> ivm20.setImageResource(R.drawable.grey)
                    21 -> ivm21.setImageResource(R.drawable.grey)
                    22 -> ivm22.setImageResource(R.drawable.grey)
                    23 -> ivm23.setImageResource(R.drawable.grey)
                    24 -> ivm24.setImageResource(R.drawable.grey)
                    25 -> ivm25.setImageResource(R.drawable.grey)
                }
            }
            selected[i] = algo // save the selected position
        }
    }

    // main function when you select a block
    fun power(i: Int, iv: ImageView) {
        if (selected.contains(i+1)) { // if the block that you select is store in the array of positions selected
            selectedRight++ // save the number of positions guessed right - max n
            iv.setImageResource(R.drawable.green) // set the position green
            if (selectedRight == n && n < 25) { // if select every position right
                score = score + 100*n // score up 100 per block
                val tvScore : TextView = findViewById(R.id.tvScore)
                tvScore.text = score.toString() // show the new score
                // set every position grey after two seconds
                GlobalScope.launch(context = Dispatchers.Main) {
                    delay(2000)
                    ivm1.setImageResource(R.drawable.grey)
                    ivm2.setImageResource(R.drawable.grey)
                    ivm3.setImageResource(R.drawable.grey)
                    ivm4.setImageResource(R.drawable.grey)
                    ivm5.setImageResource(R.drawable.grey)
                    ivm6.setImageResource(R.drawable.grey)
                    ivm7.setImageResource(R.drawable.grey)
                    ivm8.setImageResource(R.drawable.grey)
                    ivm9.setImageResource(R.drawable.grey)
                    ivm10.setImageResource(R.drawable.grey)
                    ivm11.setImageResource(R.drawable.grey)
                    ivm12.setImageResource(R.drawable.grey)
                    ivm13.setImageResource(R.drawable.grey)
                    ivm14.setImageResource(R.drawable.grey)
                    ivm15.setImageResource(R.drawable.grey)
                    ivm16.setImageResource(R.drawable.grey)
                    ivm17.setImageResource(R.drawable.grey)
                    ivm18.setImageResource(R.drawable.grey)
                    ivm19.setImageResource(R.drawable.grey)
                    ivm20.setImageResource(R.drawable.grey)
                    ivm21.setImageResource(R.drawable.grey)
                    ivm22.setImageResource(R.drawable.grey)
                    ivm23.setImageResource(R.drawable.grey)
                    ivm24.setImageResource(R.drawable.grey)
                    ivm25.setImageResource(R.drawable.grey)
                }
                GlobalScope.launch(context = Dispatchers.Main) {
                    delay(2000)
                    n++ // add one to the new number of block to guess
                    selectedRight = 0 // reset
                    selected = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0) // reset
                    setBlocks(n) // set the new number of blocks
                }
            }
        } else {
            iv.setImageResource(R.drawable.red) // set the position red if you guess wrong
            // set every position grey after two seconds
            GlobalScope.launch(context = Dispatchers.Main) {
                delay((2000).toLong())
                ivm1.setImageResource(R.drawable.grey)
                ivm2.setImageResource(R.drawable.grey)
                ivm3.setImageResource(R.drawable.grey)
                ivm4.setImageResource(R.drawable.grey)
                ivm5.setImageResource(R.drawable.grey)
                ivm6.setImageResource(R.drawable.grey)
                ivm7.setImageResource(R.drawable.grey)
                ivm8.setImageResource(R.drawable.grey)
                ivm9.setImageResource(R.drawable.grey)
                ivm10.setImageResource(R.drawable.grey)
                ivm11.setImageResource(R.drawable.grey)
                ivm12.setImageResource(R.drawable.grey)
                ivm13.setImageResource(R.drawable.grey)
                ivm14.setImageResource(R.drawable.grey)
                ivm15.setImageResource(R.drawable.grey)
                ivm16.setImageResource(R.drawable.grey)
                ivm17.setImageResource(R.drawable.grey)
                ivm18.setImageResource(R.drawable.grey)
                ivm19.setImageResource(R.drawable.grey)
                ivm20.setImageResource(R.drawable.grey)
                ivm21.setImageResource(R.drawable.grey)
                ivm22.setImageResource(R.drawable.grey)
                ivm23.setImageResource(R.drawable.grey)
                ivm24.setImageResource(R.drawable.grey)
                ivm25.setImageResource(R.drawable.grey)
            }
            // reset every value
            startiv.setImageResource(R.drawable.start)
            n = 2
            selected = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            selectedRight = 0
            isStarted = !isStarted
        }
    }

    // show if you guess right or not
    fun showPicM1(view: View) {
        power(0, ivm1)
    }

    fun showPicM2(view: View) {
        power(1, ivm2)
    }

    fun showPicM3(view: View) {
        power(2, ivm3)
    }

    fun showPicM4(view: View) {
        power(3, ivm4)
    }

    fun showPicM5(view: View) {
        power(4, ivm5)
    }

    fun showPicM6(view: View) {
        power(5, ivm6)
    }

    fun showPicM7(view: View) {
        power(6, ivm7)
    }

    fun showPicM8(view: View) {
        power(7, ivm8)
    }

    fun showPicM9(view: View) {
        power(8, ivm9)
    }

    fun showPicM10(view: View) {
        power(9, ivm10)
    }

    fun showPicM11(view: View) {
        power(10, ivm11)
    }

    fun showPicM12(view: View) {
        power(11, ivm12)
    }

    fun showPicM13(view: View) {
        power(12, ivm13)
    }

    fun showPicM14(view: View) {
        power(13, ivm14)
    }

    fun showPicM15(view: View) {
        power(14, ivm15)
    }

    fun showPicM16(view: View) {
        power(15, ivm16)
    }

    fun showPicM17(view: View) {
        power(16, ivm17)
    }

    fun showPicM18(view: View) {
        power(17, ivm18)
    }

    fun showPicM19(view: View) {
        power(18, ivm19)
    }

    fun showPicM20(view: View) {
        power(19, ivm20)
    }

    fun showPicM21(view: View) {
        power(20, ivm21)
    }

    fun showPicM22(view: View) {
        power(21, ivm22)
    }

    fun showPicM23(view: View) {
        power(22, ivm23)
    }

    fun showPicM24(view: View) {
        power(23, ivm24)
    }

    fun showPicM25(view: View) {
        power(24, ivm25)
    }

    // reset and start func
    fun reset(view: View) {
        if (isStarted) {
            ivm1.setImageResource(R.drawable.grey)
            ivm2.setImageResource(R.drawable.grey)
            ivm3.setImageResource(R.drawable.grey)
            ivm4.setImageResource(R.drawable.grey)
            ivm5.setImageResource(R.drawable.grey)
            ivm6.setImageResource(R.drawable.grey)
            ivm7.setImageResource(R.drawable.grey)
            ivm8.setImageResource(R.drawable.grey)
            ivm9.setImageResource(R.drawable.grey)
            ivm10.setImageResource(R.drawable.grey)
            ivm11.setImageResource(R.drawable.grey)
            ivm12.setImageResource(R.drawable.grey)
            ivm13.setImageResource(R.drawable.grey)
            ivm14.setImageResource(R.drawable.grey)
            ivm15.setImageResource(R.drawable.grey)
            ivm16.setImageResource(R.drawable.grey)
            ivm17.setImageResource(R.drawable.grey)
            ivm18.setImageResource(R.drawable.grey)
            ivm19.setImageResource(R.drawable.grey)
            ivm20.setImageResource(R.drawable.grey)
            ivm21.setImageResource(R.drawable.grey)
            ivm22.setImageResource(R.drawable.grey)
            ivm23.setImageResource(R.drawable.grey)
            ivm24.setImageResource(R.drawable.grey)
            ivm25.setImageResource(R.drawable.grey)
            startiv.setImageResource(R.drawable.start)
            n = 2
            selected = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
            selectedRight = 0
        } else {
            startiv.setImageResource(R.drawable.restart)
            setBlocks(n)
        }
        isStarted = !isStarted
    }
}