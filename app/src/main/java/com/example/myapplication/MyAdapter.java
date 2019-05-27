package com.example.myapplication;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public abstract class MyAdapter<T> extends BaseAdapter {
    private ArrayList<T> mData;
    private int mLayoutRes;

    public MyAdapter() {}

    public MyAdapter(ArrayList<T> mData, int mLayoutRes) {
        this.mData = mData;
        this.mLayoutRes = mLayoutRes;
    }

    public void add(T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(data);
        notifyDataSetChanged(); //自动更新item
    }

    public void add(int position, T data) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(position, data);
        notifyDataSetChanged(); //自动更新item
    }

    public void remove(T data) {
        if (mData != null)
            mData.remove(data);

        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (mData != null)
            mData.remove(position);

        notifyDataSetChanged();
    }

    public void clear() {
        if(mData != null)
            mData.clear();

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.bind(parent.getContext(), convertView, parent, mLayoutRes,position);
        bindView(holder, getItem(position));
        return holder.getItemView();
    }

    public abstract void bindView(ViewHolder holder, T obj);


    //HolderAdapter
    public static class ViewHolder {
        private Context context;
        private int postion;
        private View item;
        private SparseArray<View> mViews;


        private ViewHolder(Context context, ViewGroup parent, int LayoutRes) {
            this.context = context;
            mViews = new SparseArray<>();
            View convertView = LayoutInflater.from(context).inflate(LayoutRes, parent, false);
            convertView.setTag(this);
            item = convertView;
        }

        public static ViewHolder bind(Context context, View convertView, ViewGroup parent,
                                      int LayoutRes, int position) {
            ViewHolder holder;
            if(convertView == null) {
                holder = new ViewHolder(context, parent, LayoutRes);
            }else {
                holder = (ViewHolder) convertView.getTag();
                holder.item = convertView;
            }

            holder.postion = position;
            return holder;
        }

        public <T extends View> T getView(int id) {
            T t = (T) mViews.get(id);
            if(t == null) {
                t = (T) item.findViewById(id);
                mViews.put(id, t);
            }
            return t;
        }

        public int getItemPosition() {
            return postion;
        }

        public View getItemView() {
            return item;
        }

        public ViewHolder setText(int id, CharSequence text) {
            View view  = getView(id);
            if(view instanceof TextView) {
                ((TextView) view).setText(text);
            }
            return this;
        }

        public ViewHolder setText(int id, int text) {
            View view  = getView(id);
            if(view instanceof TextView) {
                ((TextView) view).setText(String.valueOf(text));
            }
            return this;
        }

        public ViewHolder setDate(int id, int[] date) {
            View view = getView(id);
            String str = "";

            if(date[1] < 10 && date[2] < 10)
                str = date[0]+".0"+date[1]+".0"+ date[2];
            else if(date[1] < 10)
                str = date[0]+".0"+date[1]+"."+ date[2];
            else if(date[2] < 10)
                str = date[0]+"."+date[1]+".0"+ date[2];
            else
                str = date[0]+"."+date[1]+"."+ date[2];

            if (view instanceof TextView) {
                ((TextView) view).setText(str);
            }
            return this;
        }

        public ViewHolder setImageResource(int id, int drawableRes) {
            View view = getView(id);
            if(view instanceof ImageView){
                ((ImageView)view).setImageResource(drawableRes);
            } else {
                view.setBackgroundResource(drawableRes);
            }
            return this;
        }

        public ViewHolder setOnClickListener(int id, View.OnClickListener listener) {
            getView(id).setOnClickListener(listener);
            return this;
        }

        //其他方法待扩展

    }
}
