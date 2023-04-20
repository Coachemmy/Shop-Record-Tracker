package com.example.shopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        val labelInput = findViewById<EditText>(R.id.labelInput)
        val amountInput = findViewById<EditText>(R.id.amountInput)
        val descriptionInput = findViewById<EditText>(R.id.descriptionInput)
        val amountLayout = findViewById<TextInputLayout>(R.id.amountLayout)
        val labelLayout = findViewById<TextInputLayout>(R.id.labelLayout)
        val closeBtn = findViewById<ImageButton>(R.id.closeBtn)



        labelInput.addTextChangedListener {
            if (it!!.isNotEmpty()){
                labelLayout.error = null
            }
        }

        amountInput.addTextChangedListener {
            if (it!!.isNotEmpty()){
                amountLayout.error = null
            }
        }

        val addTransactionBtn = findViewById<Button>(R.id.addTransactionBtn)
        addTransactionBtn.setOnClickListener {
            val label = labelInput.text.toString()
            val amount = amountInput.text.toString().toDoubleOrNull()
            val description = descriptionInput.text.toString()

            if (label.isEmpty())
                labelLayout.error = "please enter a valid label"

            else if (amount == null)
                amountLayout.error = "please enter a valid amount"

            else {
                val transaction = Transaction(0, label, amount, description)
                insert(transaction)
            }
        }
            closeBtn.setOnClickListener {
                finish()
            }
        }

         private fun insert(transaction: Transaction){
            val db = Room.databaseBuilder(this,
                AppDatabase::class.java,
                "transactions").build()

             GlobalScope.launch {
                 db.transactionDao().insertAll(transaction)
                 finish()
             }
        }
     }
