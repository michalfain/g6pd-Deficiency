package com.example.g6pd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoAdapter  extends RecyclerView.Adapter<InfoAdapter.MyViewHolder>{
    List<QuestionInfo> questionInfoList;
    Context context;
    boolean isPressed = false;
    public InfoAdapter(List<QuestionInfo> itemsList, Context context){
        this.context = context;
        this.questionInfoList = itemsList;
    }

    @NonNull
    @Override
    public InfoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.info_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvInfoQuestion.setText(questionInfoList.get(position).question);
        holder.tvInfoAnswer.setText(questionInfoList.get(position).answer);
        holder.llAnswer.setVisibility(View.GONE);
        holder.ivInfoIcon.setOnClickListener(v -> {
            if (isPressed == false){
                holder.ivInfoIcon.setImageResource(R.drawable.maximize);
                holder.llAnswer.setVisibility(View.VISIBLE);
                isPressed = true;

            }else {
                holder.llAnswer.setVisibility(View.GONE);
                holder.ivInfoIcon.setImageResource(R.drawable.add);
                isPressed = false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvInfoQuestion, tvInfoAnswer;
        ImageView ivInfoIcon;
        LinearLayout llAnswer, llQuestion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfoQuestion = itemView.findViewById(R.id.info_question);
            ivInfoIcon = itemView.findViewById(R.id.info_icon);
            tvInfoAnswer = itemView.findViewById(R.id.info_info);
            llAnswer = itemView.findViewById(R.id.answer_layout);
            llQuestion = itemView.findViewById(R.id.question_layout);
        }
    }
}