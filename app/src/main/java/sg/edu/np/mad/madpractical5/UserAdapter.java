package sg.edu.np.mad.madpractical5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private ArrayList<User> data;
    private ListActivity context;

    public UserAdapter(ArrayList<User> input, ListActivity activity)
    {

        this.data = input;
        this.context = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                //android.R.layout.simple_list_item_1,
                R.layout.custom_activity_list,
                parent,
                false);
        return new UserViewHolder(item);
    }

    //@Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
//        String s = data.get(position).name + data.get(position).description;
//        holder.txt.setText(s);
//        DBHandler dbHandler = new DBHandler(context,null,null,1);
//        data = dbHandler.getUsers();
        User currentUser = data.get(position);
        holder.nameTextView.setText(currentUser.getName());
        holder.txt.setText(currentUser.getDescription());
        char[] chars = (currentUser.getName()).toCharArray();
        if(chars[chars.length-1] != '7')
        {
            ImageView bigImg = holder.itemView.findViewById(R.id.bigImg);
            bigImg.setVisibility(View.GONE);

        }
        else
        {
            ImageView bigImg = holder.itemView.findViewById(R.id.bigImg);
            bigImg.setVisibility(View.VISIBLE);
        }

        ImageView smlImg = holder.itemView.findViewById(R.id.imageView15);
        smlImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating alert dialogue
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Profile");
                builder.setMessage(currentUser.getName());
                builder.setCancelable(true);
                int position1 = position;
                builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
//                        data = dbHandler.getUsers();
//                        User currentUser = data.get(position);
                        //Intent
                        Intent activity = new Intent(v.getContext(),MainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("Name", currentUser.getName());
                        extras.putString("Description", currentUser.getDescription());
                        extras.putBoolean("Followed", currentUser.getFollowed());
                        extras.putInt("Id", currentUser.getId());
                        extras.putInt("Position",position1);
                        activity.putExtras(extras);
                        context.startActivity(activity);
                    }
                });
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }


                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
