package sweet.home.pinerria1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Calendar;

import sweet.home.pinerria1.R;
import sweet.home.pinerria1.Utils.MyPrefrences;

public class ChildInfo extends AppCompatActivity {

    Button next;
    Spinner gender,gender2,gender3,gender4,gender5;
    String[] str = { "Select Gender","Male", "Female" };
    TextView DOB;
    DatePickerDialog  datePickerDialog;
    EditText childName,nationality,homeAddress;
    LinearLayout liner1;
    String genderVal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);

        Log.d("sdfsdfsdgfsdgdfgd",getIntent().getStringExtra("value"));
        Log.d("sdfsdfsdgfsgdgdfgd",getIntent().getStringExtra("id"));
        next=findViewById(R.id.next);

        gender=findViewById(R.id.gender);

        DOB=findViewById(R.id.DOB);


        childName=findViewById(R.id.childName);
        nationality=findViewById(R.id.nationality);
        homeAddress=findViewById(R.id.homeAddress);


        liner1=findViewById(R.id.liner1);

        childName.setText(getIntent().getStringExtra("name"));


        RelativeLayout relat=findViewById(R.id.relat);
        relat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderVal=gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    Intent intent=new Intent(ChildInfo.this, HealthInfo.class);
                    intent.putExtra("id",getIntent().getStringExtra("id"));
                    intent.putExtra("name",childName.getText().toString());
                    intent.putExtra("DOB",DOB.getText().toString());
                    intent.putExtra("gender",genderVal.toString());
                    intent.putExtra("nationality",nationality.getText().toString());
                    intent.putExtra("homeAddress",homeAddress.getText().toString());
                    startActivity(intent);


                }



            }
        });

//


        ArrayAdapter subcat = new ArrayAdapter(ChildInfo.this,android.R.layout.simple_spinner_item,str);
        subcat.setDropDownViewResource(R.layout.simple_spinner_item);
        gender.setAdapter(subcat);



        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                datePickerDialog = new DatePickerDialog(ChildInfo.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                DOB.setText(i2 + "/" + (i1 + 1)+ "/" + i);
                            }

                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

//


//        if (ChildList.val1==1) {
//
//        }
//        else  if (ChildList.val1==2) {
//
//        }
//        else  if (ChildList.val1==3) {
//
//        }
//        else  if (ChildList.val1==4) {
//
//        }
//        else  if (ChildList.val1==5) {
//
//        }
//        else  if (ChildList.val1==6) {
//
//        }
//        else  if (ChildList.val1==7) {
//
//        }
//        else  if (ChildList.val1==8) {
//
//        }
//        else  if (ChildList.val1==9) {
//
//        }
//        else  if (ChildList.val1==10) {
//
//        }
    }


    private boolean validate(){

        if (TextUtils.isEmpty(nationality.getText().toString()))
        {
            nationality.setError("Oops! Nationality blank");
            nationality.requestFocus();
            return false;
        }
        else if (TextUtils.isEmpty(homeAddress.getText().toString()))
        {
            homeAddress.setError("Oops! Home Address blank");
            homeAddress.requestFocus();
            return false;
        }

        return true;

    }



}
