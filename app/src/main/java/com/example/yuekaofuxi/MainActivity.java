package com.example.yuekaofuxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuekaofuxi.adapter.MyRecycleView1;
import com.example.yuekaofuxi.bean.Shangpin;
import com.example.yuekaofuxi.bean.Shop;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rlv;
    private ArrayList<Shop> list;
    private List<Shangpin> splist;
    private CheckBox cb_zong;
    private MyRecycleView1 m1;
    private double money;
    private TextView tv_zongjia;
    private int num;
    private Button bt_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            splist = new ArrayList<>();
            for (int j = 0; j <3 ; j++) {
                splist.add(new Shangpin(false,"商品"+(j+1),10.0+(j+1),1));
            }
            list.add(new Shop(false,"商家"+(i+1), splist));
        }

        setData();
    }

    private void setData() {
        m1 = new MyRecycleView1(list,MainActivity.this);
        rlv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rlv.setAdapter(m1);

        m1.setZong(new MyRecycleView1.Zong() {
            @Override
            public void shangjia() {
                boolean flag=true;
                for (Shop shop : list) {
                    for (Shangpin shangpin : shop.getSplist()) {
                        if(shangpin.isSpselect()==false)
                        {
                            flag=false;
                        }
                    }
                }
                cb_zong.setChecked(flag);
            }

            @Override
            public void shangpin() {
                boolean flag=true;
                for (Shop shop : list) {
                    for (Shangpin shangpin : shop.getSplist()) {
                        if(shangpin.isSpselect()==false)
                        {
                            flag=false;
                        }
                    }
                }
                cb_zong.setChecked(flag);
            }
        });

        m1.setSjjiage(new MyRecycleView1.Sjjiage() {
            @Override
            public void sjjg() {
                money=0;
                for (Shop shop : list) {
                    for (Shangpin shangpin : shop.getSplist()) {
                        if(shangpin.isSpselect()==true)
                        {
                            money=money+(shangpin.getNum()*shangpin.getJiage());
                        }
                    }
                }
                DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                tv_zongjia.setText("¥"+decimalFormat.format(money));

            }
        });

        m1.setSjshuliang(new MyRecycleView1.Sjshuliang() {
            @Override
            public void sjsl() {
                num=0;

                for (Shop shop : list) {
                    for (Shangpin shangpin : shop.getSplist()) {
                        if(shangpin.isSpselect()==true)
                        {
                            num++;
                        }
                    }
                }
                bt_num.setText("结钱("+ num +")");

            }
        });
    }
    private void initView() {
        rlv = (RecyclerView) findViewById(R.id.rlv);
        cb_zong = (CheckBox) findViewById(R.id.cb_zong);
        tv_zongjia = (TextView) findViewById(R.id.tv_zongjia);
        bt_num = (Button) findViewById(R.id.bt_num);

        cb_zong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                money=0;
                num=0;

                for (Shop shop : list) {
                    for (Shangpin shangpin : shop.getSplist()) {
                        if(cb_zong.isChecked())
                        {
                            shangpin.setSpselect(true);
                            money=money+(shangpin.getNum()*shangpin.getJiage());
                            num=(shop.getSplist().size())*10;

                        }
                        else
                        {
                            shangpin.setSpselect(false);
                        }
                    }
                }
                m1.notifyDataSetChanged();
                DecimalFormat decimalFormat=new DecimalFormat("######0.00");
                tv_zongjia.setText("¥"+decimalFormat.format(money));
                bt_num.setText("结钱("+num+")");
            }
        });
    }
}
