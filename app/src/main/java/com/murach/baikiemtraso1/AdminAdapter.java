package com.murach.baikiemtraso1;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdminAdapter extends ArrayAdapter<Admin> {

    public AdminAdapter(Context context, List<Admin> admins) {
        super(context, 0, admins);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Admin admin = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_list_item, parent, false);
        }

        // Lookup view for data population
        TextView adminName = convertView.findViewById(R.id.admin_name);
        TextView adminPhone = convertView.findViewById(R.id.admin_phone);

        // Populate the data into the template view using the data object
        if (admin != null) {
            adminName.setText(admin.getAdminName());
            adminPhone.setText(admin.getAdminPhone());
        }

        // Return the completed view to render on screen
        return convertView;
    }
}

