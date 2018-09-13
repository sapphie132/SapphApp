package ml.sapphie.sapphapp

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.util.*

class CalorieCounterActivity: AppCompatActivity()
{
    private val calStack: Stack<Int> = Stack();
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_calorie_counter);

        val pref = this.getPreferences(Context.MODE_PRIVATE);
        calStack.push(pref.getInt("cal", 0));
        updateCal();
    }

    private fun updateCal()
    {
        val textView = findViewById<TextView>(R.id.calTotal);
        textView.text = calStack.peek().toString();
    }

    private fun writeCal()
    {
        val pref = this.getPreferences(Context.MODE_PRIVATE);
        with(pref.edit())
        {
            putInt("cal", calStack.peek());
            apply();
        }
    }
    fun addCal(view: View)
    {
        val editText = findViewById<EditText>(R.id.editText);
        val text = editText.text.toString().trim();
        if(text!="")
        {
            editText.setText("");
            val num = text.toInt();
            calStack.push(calStack.peek()+num);
        }
        updateCal();
        writeCal();
    }
    fun resetCal(view: View)
    {
        calStack.empty();
        calStack.push(0);
        updateCal();
        writeCal();
    }

    fun undoCal(view: View)
    {
        if(calStack.size>1)
            calStack.pop();
        updateCal();
        writeCal();
    }
}