package com.example.employeeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadpter extends FirebaseRecyclerAdapter<users,myadpter.myviewholder> {

    public myadpter(@NonNull FirebaseRecyclerOptions<users> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull users users) {

        holder.txtdepartment.setText(users.getDepartment());
        holder.txtfullname.setText(users.getFullname());
//        Glide.with(holder.img.getContext()).load(users.get.....).into(holder.img);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowfile,parent,false);
        return new myviewholder(view);
    }

    class  myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView txtfullname, txtdepartment;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imageprofiler);
            txtfullname = itemView.findViewById(R.id.fullnameidr);
            txtdepartment = itemView.findViewById(R.id.departmentidr);

        }
    }

}
