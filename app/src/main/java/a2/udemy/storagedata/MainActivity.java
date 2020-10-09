package a2.udemy.storagedata;
/*
*When user re-start app old variables do not exist.
*So we use SharedPrefences using for save small data like just age or username.
*It is like a smalll database.
* It takes two parameters packagename and context
* */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //define to element
    EditText et_age;
    TextView tv_displayAge;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find to element
        et_age=findViewById(R.id.editTextNumber);
        tv_displayAge=findViewById(R.id.textView);

        sharedPreferences=this.getSharedPreferences("a2.udemy.storagedata", Context.MODE_PRIVATE);
        //getInt(key,defaultValue) -> key has to be key of keyINt
        int storedAge=sharedPreferences.getInt("storedAge",0);
        if(storedAge==0){
            tv_displayAge.setText("Your age:");
        }
        tv_displayAge.setText("Your age is "+storedAge);
    }
    public void saveButton(View view){

        if(!et_age.getText().toString().matches("")){
            int age=Integer.parseInt(et_age.getText().toString());
            tv_displayAge.setText("Your age is "+age);
            //variable is saved on db by apply() method
            //it saves last variable sharedPrefences. So variable updated.
            sharedPreferences.edit().putInt("storedAge",age).apply(); //putInt(key,value)

        }
    }

    //delete stored data
    public void deleteButton(View view){

        int storedData=sharedPreferences.getInt("storedAge",0);
        //storedData has any value
        if(storedData!=0){
            sharedPreferences.edit().remove("storedAge").apply();
            tv_displayAge.setText("Your age:");

        }

    }

}