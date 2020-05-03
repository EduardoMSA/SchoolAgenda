package com.example.schoolagenda;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GradeListAdapter extends ArrayAdapter<Grade> {

    private static final String TAG = "GradeListAdapter";
    private Context context;
    private int resource;

    public GradeListAdapter(Context context, int resource, ArrayList<Grade> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String info = getItem(position).getInfo();
        Float value = getItem(position).getPercent();
        Float grade = getItem(position).getGrade();

        Grade g = new Grade(info,value,grade);

        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(this.resource, parent, false);

        TextView tvInfo = (TextView) convertView.findViewById(R.id.infoTextView);
        TextView tvValue = (TextView) convertView.findViewById(R.id.valueTextView);
        TextView tvGrade = (TextView) convertView.findViewById(R.id.gradeTextView);

        tvGrade.setText(grade + "/100");
        tvInfo.setText(info);
        tvValue.setText(value + "%");

        return  convertView;

    }
}
