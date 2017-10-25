package com.example.yuekaofuxi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekaofuxi.AmountView;
import com.example.yuekaofuxi.R;
import com.example.yuekaofuxi.bean.Shangpin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/10/24.
 */

public class MyRecycleView2 extends RecyclerView.Adapter<MyRecycleView2.MyViewHolder> {
    private List<Shangpin> splist;
    private Context context;

    private String s;
    public MyRecycleView2(List<Shangpin> splist, Context context) {
        this.splist = splist;
        this.context = context;
    }

    @Override
    public MyRecycleView2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item2, null);
        MyViewHolder myViewHolder=new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyRecycleView2.MyViewHolder holder, final int position) {
        holder.cb_zi.setOnCheckedChangeListener(null);
        holder.tv_shangpin.setText(splist.get(position).getSp());
        holder.tv_jiage.setText(splist.get(position).getJiage()+"");

        if(splist.get(position).isSpselect()==true)
        {
            holder.cb_zi.setChecked(true);
            geshu.gs();
        }
        else
        {
            holder.cb_zi.setChecked(false);
        }

        holder.cb_zi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if( holder.cb_zi.isChecked())
                {
                    splist.get(position).setSpselect(true);

                }
                else
                {
                    splist.get(position).setSpselect(false);
                }
                splist.get(position).setSpselect(b);
                geshu.gs();
                spjiage.spjg();
                shuliang.spsl();
            }
        });

        holder.amount.setGoods_storage(50);
        final TextView etAmount=holder.amount.findViewById(R.id.etAmount);
        etAmount.setText(splist.get(position).getNum()+"");
       holder.amount.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
           @Override
            public void onAmountChange(View view, int amount) {
               //Toast.makeText(context, "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
               String s = etAmount.getText().toString();
               splist.get(position).setNum(Integer.parseInt(s));

               spjiage.spjg();
               shuliang.spsl();
           }
        });
    }
    @Override
    public int getItemCount() {
        return splist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_zi;
        private TextView tv_shangpin;
        private TextView tv_jiage;
        private AmountView amount;
        public MyViewHolder(View itemView) {
            super(itemView);
            cb_zi=itemView.findViewById(R.id.cb_zi);
            tv_shangpin=itemView.findViewById(R.id.tv_shangpin);
            tv_jiage=itemView.findViewById(R.id.tv_jiage);
            amount=itemView.findViewById(R.id.amount);
        }
    }


    private Geshu geshu;
    public void setGeshu(Geshu geshu) {
        this.geshu = geshu;
    }
    public interface Geshu
    {
        void gs();
    }


    //构选复选框计算商品的价钱
    private Spjiage spjiage;
    public void setSpjiage(Spjiage spjiage) {
        this.spjiage = spjiage;
    }
    public interface Spjiage
    {
        void spjg();
    }

    //构选复选框计算数量
    private Shuliang shuliang;
    public void setShuliang(Shuliang shuliang) {
        this.shuliang = shuliang;
    }
    public interface Shuliang
    {
        void spsl();
    }

}
