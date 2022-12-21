package joy.sarkar.agecalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
           var age = findAge(day,month , year , sd,sm,sy)
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


    fun findAge(current_date : Int, current_month : Int ,
                current_year : Int, birth_date : Int,
                birth_month : Int, birth_year : Int) : String
    {
        var current_date = current_date
        var current_month = current_month
        var current_year = current_year
        var birth_date = birth_date
        var birth_month = birth_month
        var birth_year = birth_year
        // days of every month
        var months =  arrayListOf<Int>(31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31)

        if (birth_date > current_date) {
            current_date += months.get(birth_month-1)
            current_month -= 1
        }


        if (birth_month > current_month) {
            current_year -= 1
            current_month += 12
        }

        val calculated_date = current_date - birth_date
        val calculated_month = current_month - birth_month
        val calculated_year = current_year - birth_year

       var age = " $calculated_year YEARS $calculated_month MONTHS $calculated_date DAYS"
        return age
}
}
