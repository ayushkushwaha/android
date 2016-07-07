package com.techvisionit.recyclerview_ex;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.support.v4.app.ActivityCompat.*;
import static android.support.v4.app.ActivityOptionsCompat.*;

/**
 * Created by Ayush i am - ON on 15-May-16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    String [] name={"Ayush Kushwaha - android", "Shael - open span", "Yog - pmp", "Manish - open span", "Vim - java"};

    Context context;
    LayoutInflater inflater;

    public ContactAdapter(Context context) {

        this.context=context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v=inflater.inflate(R.layout.custom_view, parent, false);

        ContactViewHolder viewHolder = new ContactViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        holder.name.setText(name[position]);
        holder.name.setOnClickListener(clickListener);
        holder.name.setTag(holder);
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ContactViewHolder vholder = (ContactViewHolder) v.getTag();
            int position = vholder.getPosition();





/*
            Pair<TextView, String> Title = Pair.create((TextView) vholder.name, "titleTrans");

            ActivityOptionsCompat options = makeSceneTransitionAnimation(context,Title);
            Intent intent = new Intent(context, details.class);
            context.startActivity(context, intent, options.toBundle());
*/



           // Toast.makeText(context,"on click current position "+position,Toast.LENGTH_LONG ).show();

        }
    };

    @Override
    public int getItemCount() {
        return name.length;
    }
}
