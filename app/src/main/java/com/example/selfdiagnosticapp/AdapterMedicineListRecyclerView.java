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

import org.w3c.dom.Text;

import java.util.List;

public class AdapterMedicineListRecyclerView extends RecyclerView.Adapter<AdapterMedicineListRecyclerView.ViewHolder>{

    Context context;

    List<String> medAdultUsage,medChildUsage,medDisease,medImage,medName,medPrecautions,medSideEffects;

    public AdapterMedicineListRecyclerView(Context context, List<String> medAdultUsage, List<String> medChildUsage, List<String> medDisease, List<String> medImage, List<String> medName, List<String> medPrecautions, List<String> medSideEffects) {
        this.context = context;
        this.medAdultUsage = medAdultUsage;
        this.medChildUsage = medChildUsage;
        this.medDisease = medDisease;
        this.medImage = medImage;
        this.medName = medName;
        this.medPrecautions = medPrecautions;
        this.medSideEffects = medSideEffects;
    }

    @NonNull
    @Override
    public AdapterMedicineListRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.medicine_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMedicineListRecyclerView.ViewHolder holder, int position) {
        holder.name_textView.setText(medName.get(position));
        holder.diseaseName_textView.setText(medDisease.get(position));

        Picasso.get().load(medImage.get(position)).into(holder.medicine_imageView);

        holder.medicine_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MedicineDetails.class);
                intent.putExtra("MEDICINE_NAME",medName.get(holder.getAdapterPosition()));
                intent.putExtra("MEDICINE_IMAGE",medImage.get(holder.getAdapterPosition()));
                intent.putExtra("MEDICINE_ADULT_USAGE",medAdultUsage.get(holder.getAdapterPosition()));
                intent.putExtra("MEDICINE_CHILD_USAGE",medChildUsage.get(holder.getAdapterPosition()));
                intent.putExtra("MEDICINE_SIDE_EFFECTS",medSideEffects.get(holder.getAdapterPosition()));
                intent.putExtra("MEDICINE_PRECAUTIONS",medPrecautions.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_textView;
        TextView diseaseName_textView;
        ImageView medicine_imageView;


        RelativeLayout medicine_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView = (TextView) itemView.findViewById(R.id.name_textView);
            diseaseName_textView = (TextView) itemView.findViewById(R.id.diseaseName_textView);
            medicine_imageView = (ImageView) itemView.findViewById(R.id.medicine_imageView);
            medicine_layout = (RelativeLayout) itemView.findViewById(R.id.medicine_layout);
        }
    }
}
