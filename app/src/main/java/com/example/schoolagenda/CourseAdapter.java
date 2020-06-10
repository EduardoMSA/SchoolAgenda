package com.example.schoolagenda;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CourseAdapter extends ArrayAdapter<Course> {

    private Context context;
    private int resource;

    public CourseAdapter(Context context, int resource, ArrayList<Course> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final String name = getItem(position).getName();
        String prof = getItem(position).getProf();
        final Classes schedule = getItem(position).getClassSchedule();
        final String email = getItem(position).getEmail();

        Course g = new Course(name,prof,schedule,email);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textCourse);
        TextView tvProfessor = (TextView) convertView.findViewById(R.id.textProfessor);
        Button sc = (Button) convertView.findViewById(R.id.buttonCalendar);
        Button contact = (Button) convertView.findViewById(R.id.buttonEmail);

        tvName.setText(name);
        tvProfessor.setText(prof);
        sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "";
                msg += "Days: \n";
                for(int i = 0; i<schedule.getDays().length; i++){
                    msg += schedule.getDays()[i] + "\n";
                }
                msg += "\nTime:\n";
                msg += schedule.getStartHour() + ":" + schedule.getStartMinute() + " to\n" + schedule.getEndHour() + ":" + schedule.getEndMinute();
                new AlertDialog.Builder(context).setTitle(name).setMessage(msg).setIcon(android.R.drawable.ic_dialog_info).show();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mail = new Intent(context, MailActivity.class);
                mail.putExtra("mail",email);
                mail.putExtra("name",name);
                context.startActivity(mail);

            }
        });

        return  convertView;

    }


}
