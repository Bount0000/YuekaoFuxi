package com.example.yuekaofuxi.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuekaofuxi.R;
import com.example.yuekaofuxi.bean.Shangpin;
import com.example.yuekaofuxi.bean.Shop;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 祝文 on 2017/10/24.
 */

public class MyRecycleView1 extends RecyclerView.Adapter<MyRecycleView1.MyViewHolder> {
    private ArrayList<Shop> list;
    private Context context;

    public MyRecycleView1(ArrayList<Shop> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyRecycleView1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item1, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyRecycleView1.MyViewHolder holder, final int position) {

        holder.tv_shangjia.setText(list.get(position).getShangjia());

        final List<Shangpin> splist = list.get(position).getSplist();
        System.out.println("-----"+splist.size());
        final MyRecycleView2 m2=new MyRecycleView2(splist,context);
        holder.rlv_zi.setLayoutManager(new LinearLayoutManager(context));
        holder.rlv_zi.setAdapter(m2);

        boolean flag=true;
        for (Shangpin shangpin : splist) {
            if(shangpin.isSpselect()==false)
            {
                flag=false;
            }
        }
        holder.cb.setChecked(flag);

        m2.setGeshu(new MyRecycleView2.Geshu() {
            @Override
            public void gs() {
                boolean flag=true;
                for (Shangpin shangpin : splist) {
                    if(shangpin.isSpselect()==false)
                    {
                        flag=false;
                    }
                }
                holder.cb.setChecked(flag);
                zong.shangpin();
            }
        });

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Shangpin shangpin : splist) {
                    if(holder.cb.isChecked())
                    {
                        shangpin.setSpselect(true);
                    }
                    else
                    {
                        shangpin.setSpselect(false);
                    }
                }
                m2.notifyDataSetChanged();
                zong.shangjia();
                //商家价格接口
                sjjiage.sjjg();
                //商家数量接口
                sjshuliang.sjsl();
            }
        });

        //计算价钱
        m2.setSpjiage(new MyRecycleView2.Spjiage() {
            @Override
            public void spjg() {
                sjjiage.sjjg();
            }
        });
        //计算数量
        m2.setShuliang(new MyRecycleView2.Shuliang() {
            @Override
            public void spsl() {
                sjshuliang.sjsl();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb;
        private TextView tv_shangjia;
        private RecyclerView rlv_zi;
        public MyViewHolder(View itemView) {
            super(itemView);
            cb=itemView.findViewById(R.id.cb);
            tv_shangjia=itemView.findViewById(R.id.tv_shangjia);
            rlv_zi=itemView.findViewById(R.id.rlv_zi);
        }
    }

    //总的复选框
    private Zong zong;
    public void setZong(Zong zong) {
        this.zong = zong;
    }
    public interface Zong
    {
        void shangjia();
        void shangpin();
    }


    //商家复选框
    private Sjjiage sjjiage;
    public void setSjjiage(Sjjiage sjjiage) {
        this.sjjiage = sjjiage;
    }
    public interface Sjjiage
    {
        void sjjg();
    }

    //商家复选框数量
    private Sjshuliang sjshuliang;
    public void setSjshuliang(Sjshuliang sjshuliang) {
        this.sjshuliang = sjshuliang;
    }
    public interface Sjshuliang
    {
        void sjsl();
    }
}
