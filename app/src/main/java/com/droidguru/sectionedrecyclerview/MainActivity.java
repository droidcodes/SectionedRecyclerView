package com.droidguru.sectionedrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recycler_main)
    RecyclerView recyclerMain;

    LinearLayoutManager linearLayoutManager;
    ArrayList<HashMap<String,String>> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        getData();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerMain.setLayoutManager(linearLayoutManager);
        DataAdapter dataAdapter = new DataAdapter(this,dataList);
        recyclerMain.setAdapter(dataAdapter);

        RecyclerItemDecoration recyclerItemDecoration = new RecyclerItemDecoration(this,getResources().getDimensionPixelSize(R.dimen.header_height),true,getSectionCallback(dataList));
        recyclerMain.addItemDecoration(recyclerItemDecoration);
    }


    private RecyclerItemDecoration.SectionCallback getSectionCallback(final ArrayList<HashMap<String,String>> list)
    {
        return new RecyclerItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int pos) {
                return pos==0 || list.get(pos).get("Title")!=list.get(pos-1).get("Title");
            }

            @Override
            public String getSectionHeaderName(int pos) {
                HashMap<String,String> dataMap = list.get(pos);
                return dataMap.get("Title");
            }
        };
    }

    private void getData()
    {

        HashMap<String,String> dataMAp1 = new HashMap<>();
        dataMAp1.put("Title","Around the corner");
        dataMAp1.put("Desc1","Meaning: A time or event that is coming very soon.");
        dataMAp1.put("Desc2","Example: Its still cold today,but spring is just around the corner.");
        dataList.add(dataMAp1);

        HashMap<String,String> dataMAp2 = new HashMap<>();
        dataMAp2.put("Title","Habitat");
        dataMAp2.put("Desc1","Meaning: The natural environment in which an animal or plant usually lives.");
        dataMAp2.put("Desc2","Example: With so many areas around city being cut down,a lot of wildlife is losing its natural habitat.");
        dataList.add(dataMAp2);

        HashMap<String,String> dataMAp3 = new HashMap<>();
        dataMAp3.put("Title","Sabotage");
        dataMAp3.put("Desc1","Meaning: To intentionally damage or destroy something.");
        dataMAp3.put("Desc2","Example: Bad habits leads to self-sabotage.");
        dataList.add(dataMAp3);

        HashMap<String,String> dataMAp4 = new HashMap<>();
        dataMAp4.put("Title","Fuming");
        dataMAp4.put("Desc1","Meaning: Feeling,showing or expressing great anger.");
        dataMAp4.put("Desc2","Example: Smith was left fuming following the security breach.");
        dataList.add(dataMAp4);

        HashMap<String,String> dataMAp5 = new HashMap<>();
        dataMAp5.put("Title","Transcend");
        dataMAp5.put("Desc1","Meaning: Be or go beyond the range or limits of (a field of activity or conceptual sphere)");
        dataMAp5.put("Desc2","Example: This was an issue transcending party politics.");
        dataList.add(dataMAp5);

    }
}
