package com.example.g6pd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String answer;
        if(questionInfoList.get(position).answer.contains("*")){
            answer = questionInfoList.get(position).answer.substring(0 , questionInfoList.get(position).answer.indexOf("*")) + System.getProperty("line.separator") + Constants.addDisclaimer;
            holder.tvInfoAnswer.setText(answer);

        }else {
            holder.tvInfoAnswer.setText(questionInfoList.get(position).answer);

        }
        holder.tvInfoQuestion.setText(questionInfoList.get(position).question);
        holder.llAnswer.setVisibility(View.GONE);
        holder.llQuestion.setOnClickListener(v -> {
            if (isPressed == false){
                holder.llAnswer.setVisibility(View.VISIBLE);
                isPressed = true;

            }else {
                holder.llAnswer.setVisibility(View.GONE);
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
        LinearLayout llAnswer, llQuestion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfoQuestion = itemView.findViewById(R.id.info_question);
            tvInfoAnswer = itemView.findViewById(R.id.info_info);
            llAnswer = itemView.findViewById(R.id.answer_layout);
            llQuestion = itemView.findViewById(R.id.question_layout);
        }
    }
}