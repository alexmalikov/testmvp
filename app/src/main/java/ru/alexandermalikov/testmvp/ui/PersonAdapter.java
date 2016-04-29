package ru.alexandermalikov.testmvp.ui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.alexandermalikov.testmvp.R;
import ru.alexandermalikov.testmvp.web.data.Person;

public class PersonAdapter extends ArrayAdapter<Person> {

    private List<Person> mPersonList;

    public PersonAdapter(Context context, int resource) {
        super(context, resource);
        mPersonList = new ArrayList<>();
    }

    public void setPersonList(List<Person> personList) {
        mPersonList.addAll(personList);
        notifyDataSetChanged();
    }

    public Person getPerson(int position) {
        return mPersonList.get(position);
    }

    @Override
    public Person getItem(int position) {
        return mPersonList.get(position);
    }

    @Override
    public int getCount() {
        return mPersonList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_person_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(getItem(position).getName());
        return convertView;
    }


    private static class ViewHolder {
        TextView tvName;
    }
}
