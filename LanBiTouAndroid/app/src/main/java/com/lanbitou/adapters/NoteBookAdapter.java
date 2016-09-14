package com.lanbitou.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lanbitou.R;
import com.lanbitou.entities.NoteBookEntity;
import com.lanbitou.entities.NoteEntity;
import com.lanbitou.util.FileUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by joyce on 16-5-12.
 */
public class NoteBookAdapter extends BaseAdapter{

    private List<NoteBookEntity> listItems;
    private LayoutInflater inflater;

    private static FileUtil noteFileUtil = new FileUtil("/note", "/note.lan");
    private Gson gson = new Gson();
    private static Type noteListType = new TypeToken<List<NoteEntity>>() {}.getType();


    public NoteBookAdapter(Activity activity, List<NoteBookEntity> listItems) {
        this.listItems = listItems;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.notebookitem, null);
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView number = (TextView) convertView.findViewById(R.id.number);

            holder = new ViewHolder();
            holder.title = title;
            holder.number = number;

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        NoteBookEntity nbe = listItems.get(position);
        int bid = nbe.getBid();
        holder.title.setText(nbe.getName());

        int count = 0;
        String notelistJson = "";
        if (!(notelistJson = noteFileUtil.read()).equals("")) {
            List<NoteEntity> localListItems = gson.fromJson(notelistJson, noteListType);
            for (NoteEntity noteEntity : localListItems) {
                if (noteEntity.getBid() == bid) {
                    count++;
                }
            }
        }
        holder.number.setText(count + "条笔记");

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView number;
    }
}
