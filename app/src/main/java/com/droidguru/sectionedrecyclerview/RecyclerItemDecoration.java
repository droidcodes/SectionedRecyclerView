package com.droidguru.sectionedrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {

    Context context;
    int headerOffset;
    boolean sticky;
    SectionCallback sectionCallback;
    View headerView;
    TextView tvTitle;
    public RecyclerItemDecoration(Context con,int headerHeight,boolean isSticky,SectionCallback callback)
    {
        context = con;
        headerOffset = headerHeight;
        sticky = isSticky;
        sectionCallback = callback;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if(sectionCallback.isSection(pos))
        {
            outRect.top = headerOffset;
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if(headerView==null)
        {
            headerView = inflateHeader(parent);
            tvTitle = (TextView)headerView.findViewById(R.id.text_header_name);
            fixLayoutSize(headerView,parent);
        }
        String prevTitle = "";
        for(int i=0;i<parent.getChildCount();i++)
        {
            View child = parent.getChildAt(i);
            int childPos = parent.getChildAdapterPosition(child);
            String title = sectionCallback.getSectionHeaderName(childPos);
            tvTitle.setText(title);
            if(!prevTitle.equalsIgnoreCase(title) || sectionCallback.isSection(childPos))
            {
                drawHeader(c,child,headerView);
                prevTitle = title;
            }
        }

    }

    private void drawHeader(Canvas c, View child, View headerView) {
        c.save();
        if(sticky)
        {
            c.translate(0,Math.max(0,child.getTop()-headerView.getHeight()));
        }else {
            c.translate(0,child.getTop()-headerView.getHeight());
        }
        headerView.draw(c);
        c.restore();
    }


    public void fixLayoutSize(View view, ViewGroup viewGroup)
    {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(),View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(),View.MeasureSpec.UNSPECIFIED);
        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,viewGroup.getPaddingLeft()+viewGroup.getPaddingRight(),view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,viewGroup.getPaddingTop()+viewGroup.getPaddingBottom(),view.getLayoutParams().height);

        view.measure(childWidth,childHeight);
        view.layout(0,0,view.getMeasuredWidth(),view.getMeasuredHeight());
    }
    private View inflateHeader(RecyclerView recyclerView)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.row_section_header,recyclerView,false);
        return view;
    }
    public interface SectionCallback{
        boolean isSection(int pos);
        String getSectionHeaderName(int pos);
    }
}
