package com.example.record;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editId;
    Button btnAddData,btnviewAll,btnupdate,btndelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        editName = findViewById(R.id.editText);
        editSurname = findViewById(R.id.editText2);
        editMarks = findViewById(R.id.editText3);
        editId = findViewById(R.id.editText4);
        btnAddData = findViewById(R.id.button);
        btnviewAll = findViewById(R.id.button2);
        btnupdate = findViewById(R.id.button3);
        btndelete = findViewById(R.id.button5);
        AddData();
        viewAll();
        UpdateData();6666666666666666
        DeleteData();66666666666666666666666
    }

    public void DeleteData() {
    btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer deleteRows = myDb.deleteData(editId.getText().toString());
            if (deleteRows > 0)
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();

        }
    });
    }

    public void UpdateData()
    {
        btnupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isUpdate = myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
               else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();

            }
        });
    }




    public void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isInserted = myDb.insertData(editName.getText().toString(),editSurname.getText().toString(),editMarks.getText().toString());
               if (isInserted == true)
                   Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error","Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id :"+ res.getString(0)+"\n");
                    buffer.append("Name :"+ res.getString(1)+"\n");
                    buffer.append("Surname :"+ res.getString(2)+"\n");
                    buffer.append("Marks :"+ res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}

