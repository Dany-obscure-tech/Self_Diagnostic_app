package com.example.selfdiagnosticapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDiseaseListRecyclerView extends RecyclerView.Adapter<AdapterDiseaseListRecyclerView.ViewHolder>{

    Context context;

    List<String> name, medicine,symptoms;

    public AdapterDiseaseListRecyclerView(Context context, List<String> name, List<String> medicine, List<String> symptoms) {
        this.context = context;
        this.name = name;
        this.medicine = medicine;
        this.symptoms = symptoms;
    }

    @NonNull
    @Override
    public AdapterDiseaseListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.disease_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDiseaseListRecyclerView.ViewHolder holder, int position) {
        holder.name_textView.setText(name.get(position));
        holder.medicineName_textView.setText(medicine.get(position));
        holder.disease_relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DiseaseDetails.class);
                intent.putExtra("DISEASE_NAME",name.get(holder.getAdapterPosition()));
                intent.putExtra("DISEASE_MED",medicine.get(holder.getAdapterPosition()));
                intent.putExtra("DISEASE_SYMPTOMS",symptoms.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_textView,medicineName_textView;
        RelativeLayout disease_relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView = (TextView) itemView.findViewById(R.id.name_textView);
            medicineName_textView = (TextView) itemView.findViewById(R.id.medicineName_textView);
            disease_relativeLayout = (RelativeLayout) itemView.findViewById(R.id.disease_relativeLayout);
        }
    }
}
