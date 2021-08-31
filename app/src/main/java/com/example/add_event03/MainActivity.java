package com.example.add_event03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String name,remark;
    private Button add_event_begin_time,add_event_end_time,add_event_date;
    private TextView choose_event_color,choose_event_notification,add_event_secure;
    private EditText add_event_name,add_event_remark;
    //---color selection 全域變數---
    private CharSequence[] colorSequence ;
    private int colornumber;
    //---notification---
    private CharSequence[] notificationTimeSequence;
    //---begin & end time setting-----
    private int beginTimeHour,beginTimeMinute,endTimeHour,endTimeMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_event_begin_time = findViewById(R.id.add_event_beginTime);
        add_event_end_time = findViewById(R.id.add_event_endTime);
        add_event_date = findViewById(R.id.add_event_pickdate);
        choose_event_color = findViewById(R.id.add_event_chooseColor);
        choose_event_notification = findViewById(R.id.add_event_notification);
        add_event_secure = findViewById(R.id.add_event_secure);
        add_event_name = findViewById(R.id.add_event_name);
        add_event_remark = findViewById(R.id.add_event_remark);

        //edittext的存放--!
        name = add_event_name.getText().toString();
        remark = add_event_remark.getText().toString();
        //date picker builder--!
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Select day");
        final MaterialDatePicker materialDatePicker = builder.build();
        //---add_event_date---onclicklistener
        add_event_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getSupportFragmentManager(),"Date_picker");
            }
        });
        //date picker positive btn on click listener
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        add_event_date.setText(materialDatePicker.getHeaderText());
                    }
                });

                //---color selection--------------
        colorSequence = new CharSequence[]{"red","orange","yellow","teal","green","blue","navy","light purple"};
        choose_event_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("選取顏色").setCancelable(true).setSingleChoiceItems(colorSequence, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        colornumber = which;
                    }
                }).setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(colornumber==0){choose_event_color.setBackgroundResource(R.color.red);choose_event_color.setText("red");}
                        else if(colornumber==1){choose_event_color.setBackgroundResource(R.color.orange);choose_event_color.setText("orange");}
                        else if(colornumber==2){choose_event_color.setBackgroundResource(R.color.yellow);choose_event_color.setText("yellow");}
                        else if(colornumber==3){choose_event_color.setBackgroundResource(R.color.teal_200);choose_event_color.setText("teal");}
                        else if(colornumber==4){choose_event_color.setBackgroundResource(R.color.green);choose_event_color.setText("green");}
                        else if(colornumber==5){choose_event_color.setBackgroundResource(R.color.blue);choose_event_color.setText("blue");}
                        else if(colornumber==6){choose_event_color.setBackgroundResource(R.color.navy);choose_event_color.setText("navy");}
                        else {choose_event_color.setBackgroundResource(R.color.purple_200);choose_event_color.setText("purple");}
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        //------color selection end---------------------------------------------------------------------

        //------notification----------------------------------------------------------------------------
        notificationTimeSequence = new CharSequence[] {"10分鐘","30分鐘","1小時","3小時","不提醒"};
        choose_event_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("選取時間").setSingleChoiceItems(notificationTimeSequence, 4, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //not finish code
                    }
                }).setIcon(R.drawable.ic_baseline_notifications_none_24)
                        .setBackground(getResources().getDrawable(R.drawable.rounded))
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //not finish code
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            //not finish code
                    }
                });
                builder.show();
            }
        });
        //----------------------notification end----------------------------------
        //------secure---的跳轉
        add_event_secure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void popBeginTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                beginTimeHour = selectHour;
                beginTimeMinute = selectMinute;
                add_event_begin_time.setText(String.format(Locale.getDefault(),"%02d : %02d",beginTimeHour,beginTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,beginTimeHour,beginTimeMinute,true);

        timePickerDialog.setTitle("選取時間");
        timePickerDialog.show();
    }

    public void popEndTime(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                endTimeHour = selectHour;
                endTimeMinute = selectMinute;
                add_event_end_time.setText(String.format(Locale.getDefault(),"%02d : %02d",endTimeHour,endTimeMinute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,endTimeHour,endTimeMinute,true);

        timePickerDialog.setTitle("選取時間");
        timePickerDialog.show();

    }
}