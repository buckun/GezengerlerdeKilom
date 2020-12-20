package com.buckun.gezegenlerdekilomuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener{

    private val WEIGHT_TO_POUND = 2.2045
    private val MARS = 0.38
    private val VENUS = 0.91
    private val JUPITER = 2.34
    private val MERCURY = 0.38
    private val EARTH = 1.0
    private val SATURN = 1.06
    private val URANUS = 0.92
    private val NEPTUNE = 1.19
    private val PLUTO = 0.06
    private val POUND_TO_WEIGHT = 0.45359237

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var userWeight = girilecekKiloMiktarı.text

        checkBoxMars.setOnClickListener(this)
        checkBoxVenus.setOnClickListener(this)
        checkBoxJupiter.setOnClickListener(this)


        /*hesapButton.setOnClickListener{
            var findPound = weightToPound(userWeight.toString().toDouble())
            var findMarsPound = findPound * MARS
            var findMarsWeight =poundToWeight(findMarsPound)
            hesaplanaKilo.text = findMarsWeight.formatla(2).toString()
        }*/
    }

    private fun weightToPound(weight:Double) :Double {
        return weight * WEIGHT_TO_POUND
    }

    private fun poundToWeight(pound:Double) :Double {
        return pound * POUND_TO_WEIGHT
    }

    private fun Double.formatla(formatForWeight:Int) = java.lang.String.format("%.${formatForWeight}f",this)


    override fun onClick(v: View?) {
        v as CheckBox
        var isChecked = v.isChecked
        if(TextUtils.isEmpty(girilecekKiloMiktarı.text.toString())) {
            Toast.makeText(this,"Lütfen bir sayi giriniz",Toast.LENGTH_LONG).show()
            return
        }
        var kullanıcıKilo= weightToPound(girilecekKiloMiktarı.text.toString().toDouble())

        when(v.id) {
            R.id.checkBoxMars -> {
                if (isChecked) {
                    checkBoxJupiter.isChecked = false
                    checkBoxVenus.isChecked = false
                    hesaplaAgirlik(kullanıcıKilo,MARS)
                }

            }
            R.id.checkBoxVenus -> {
                if (isChecked) {
                    checkBoxJupiter.isChecked = false
                    checkBoxMars.isChecked = false
                    hesaplaAgirlik(kullanıcıKilo,VENUS)
                }
            }

            R.id.checkBoxJupiter -> {
                if (isChecked) {
                    checkBoxMars.isChecked = false
                    checkBoxVenus.isChecked = false
                    hesaplaAgirlik(kullanıcıKilo,JUPITER)
                }

            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(!TextUtils.isEmpty(girilecekKiloMiktarı.text.toString())) {
            outState.putString("FINDWEIGHT", hesaplanaKilo.text.toString())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        hesaplanaKilo.text = savedInstanceState?.getString("FINDWEIGHT")
    }


    fun hesaplaAgirlik(pound: Double, gezgeneGorePoundDegeri:Double) {
        var sonuctoKilo =poundToWeight(pound * gezgeneGorePoundDegeri)
        hesaplanaKilo.text = sonuctoKilo.formatla(2).toString() + " kg"

    }
}
