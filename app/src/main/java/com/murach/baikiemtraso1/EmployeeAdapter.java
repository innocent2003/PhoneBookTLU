// EmployeeAdapter.java
package com.murach.baikiemtraso1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    private Context context;
    private int resource;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, int resource, List<Employee> employeeList) {
        super(context, resource, employeeList);
        this.context = context;
        this.resource = resource;
        this.employeeList = employeeList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Employee employee = employeeList.get(position);

        ImageView employeeAvatar = convertView.findViewById(R.id.employeeAvatar);
        TextView employeeName = convertView.findViewById(R.id.employeeName);

        employeeName.setText(employee.getEmployeeName());
        // Assuming employeeAvatar is a URL
        Picasso.get().load(employee.getEmployeeAvatar()).into(employeeAvatar);

        return convertView;
    }
}
