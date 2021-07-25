package com.mahdi.tesh3ilisavingbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {
    private List<Member> members = new ArrayList<>();
    private List<Income> incomes = new ArrayList<>();

    private OnItemClickListener listener;


    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_item, parent, false);
        return new MemberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        Member currentMember = members.get(position);
        int allMoneyPaid = 0;
        int memberId = currentMember.getId();
        List<Income> incomesWzSpcfcId = new ArrayList<>();

        for (Income x : incomes) {
            if (x.getMemberId() == memberId) {
                incomesWzSpcfcId.add(x);

            }
        }
        for (Income x : incomesWzSpcfcId) {
            allMoneyPaid += x.getMonthlyIn() + x.getMonthlyInCoal();
        }


        holder.textViewName.setText(currentMember.getMemberName());

        if (incomesWzSpcfcId.size() != 0) {
            String[] months={"January","February","March","April","May","June","July","Augest","September","October","November","December"};
            holder.textViewLastMonth.setText(months[(incomesWzSpcfcId.get(incomesWzSpcfcId.size() - 1).getPaymentDate())-1]);
            holder.textViewLastMonth.setVisibility(View.VISIBLE);
            holder.allMoneyPaid.setText(allMoneyPaid+" LBP");
        } else {
            holder.textViewLastMonth.setVisibility(View.INVISIBLE);
            holder.allMoneyPaid.setText("0 LBP");
        }

    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    public void setMembers(List<Member> members) {
        this.members = members;

        notifyDataSetChanged();
    }

    public void setIncomes(List<Income> incomes) {

        this.incomes = incomes;
        notifyDataSetChanged();
    }

    class MemberHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLastMonth;
        private TextView allMoneyPaid;


        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.member_name);
            textViewLastMonth = itemView.findViewById(R.id.last_month);
            allMoneyPaid = itemView.findViewById(R.id.total_money);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(members.get(position));
                    }
                }
            });
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Member member);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
