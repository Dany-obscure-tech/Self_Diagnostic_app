package com.example.selfdiagnosticapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDoctorListRecyclerView extends RecyclerView.Adapter<AdapterDoctorListRecyclerView.ViewHolder>{

    Context context;

    List<String> name,image, specialist,contactNo,address;

    public AdapterDoctorListRecyclerView(Context context, List<String> name, List<String> image, List<String> specialist, List<String> contactNo, List<String> address) {
        this.context = context;
        this.name = name;
        this.image = image;
        this.specialist = specialist;
        this.contactNo = contactNo;
        this.address = address;
    }

    @NonNull
    @Override
    public AdapterDoctorListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.doctors_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDoctorListRecyclerView.ViewHolder holder, int position) {
        holder.name_textView.setText(name.get(position));
        holder.specialist_textView.setText(specialist.get(position));
        holder.contactNo_textView.setText(contactNo.get(position));
        holder.address_textView.setText(address.get(position));

        Picasso.get().load(image.get(position)).into(holder.doc_imageView);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_textView;
        TextView specialist_textView;
        TextView contactNo_textView;
        TextView address_textView;

        ImageView doc_imageView;

        RelativeLayout medicine_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView = (TextView) itemView.findViewById(R.id.name_textView);
            specialist_textView = (TextView) itemView.findViewById(R.id.specialist_textView);
            contactNo_textView = (TextView) itemView.findViewById(R.id.contactNo_textView);
            address_textView = (TextView) itemView.findViewById(R.id.address_textView);

            doc_imageView = (ImageView) itemView.findViewById(R.id.doc_imageView);

            medicine_layout = (RelativeLayout) itemView.findViewById(R.id.medicine_layout);
        }
    }
}
