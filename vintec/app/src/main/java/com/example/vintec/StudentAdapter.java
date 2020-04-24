package com.example.vintec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>{
    private Context mContext;
    private List<StudenItems> items;

    public StudentAdapter(Context mContext,List<StudenItems> items){
        this.mContext = mContext;
        this.items= items;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_single_item, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);

        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        StudenItems item = items.get(holder.getAdapterPosition());
        holder.name.setText(item.getName());
        holder.certificate.setText(item.getCertificate());
        holder.course.setText(item.getCourse());
        holder.duration.setText(item.getDuration());
        holder.issuedate.setText(item.getIssuedate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,certificate,course,duration,issuedate;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            certificate = (TextView) view.findViewById(R.id.certificate);
            course = (TextView) view.findViewById(R.id.course);
            duration = (TextView) view.findViewById(R.id.duration);
            issuedate = (TextView) view.findViewById(R.id.issudate);
        }
    }
}
