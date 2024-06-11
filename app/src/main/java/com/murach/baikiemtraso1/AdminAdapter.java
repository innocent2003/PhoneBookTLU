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

public class AdminAdapter extends ArrayAdapter<Admin> {
    private Context context;
    private List<Admin> admins;

    public AdminAdapter(Context context, List<Admin> admins) {
        super(context, R.layout.list_item_admin, admins);
        this.context = context;
        this.admins = admins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_admin, parent, false);
        }

        Admin admin = admins.get(position);

        ImageView adminLogoImageView = convertView.findViewById(R.id.adminLogoImageView);
        TextView adminNameTextView = convertView.findViewById(R.id.adminNameTextView);

        adminNameTextView.setText(admin.getAdminName());

        // Use Picasso to load the admin logo
        Picasso.get().load(admin.getAdminLogo()).into(adminLogoImageView);

        return convertView;
    }
}
