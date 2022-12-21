package joy.sarkar.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myBtn: Button = findViewById(R.id.btnDate)
        myBtn.setOnClickListener {view->
            datePik(view)
        }
        val resetBtn : Button = findViewById(R.id.btnReset)
        resetBtn.setOnClickListener {view->
            reset(view)
            Toast.makeText(this , "Date Reset" , Toast.LENGTH_LONG).show()
        }

    }

    fun datePik(view: View) {
        val myDate = Calendar.getInstance()
        val year = myDate.get(Calendar.YEAR)
        val month = myDate.get(Calendar.MONTH)
        val day = myDate.get(Calendar.DAY_OF_MONTH)
       DatePickerDialog(this,DatePickerDialog.OnDateSetListener { _, sy, sm, sd ->
           val selectedDate = "$sd / ${sm+1} / $sy"
           val dateLine : TextView = findViewById(R.id.sd)
           dateLine.text = selectedDate
           val age = (year-sy).toString()
           val ageLine : TextView = findViewById(R.id.ageView)
           ageLine.text = age
       } , year , month , day).show()

    }

    fun reset(view : View){
        val dateLine : TextView = findViewById(R.id.sd)
        dateLine.text = ""
        val ageLine : TextView = findViewById(R.id.ageView)
        ageLine.text = ""
    }



}