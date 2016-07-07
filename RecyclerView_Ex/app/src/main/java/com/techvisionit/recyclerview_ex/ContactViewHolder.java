package com.techvisionit.recyclerview_ex;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ayush i am - ON on 15-May-16.
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {
    TextView name, description;
    Button readmore;

    public ContactViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.textView);
        description = (TextView) itemView.findViewById(R.id.textView2);
        readmore = (Button) itemView.findViewById(R.id.button);

    }
}
