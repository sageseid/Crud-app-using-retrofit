package com.noel_inc.crudappinterview.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noel_inc.crudappinterview.R;
import com.noel_inc.crudappinterview.activities.UpdatePostActivity;
import com.noel_inc.crudappinterview.model.GetPosts;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    private List<GetPosts> dataList;
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView Title;
        TextView body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);


        }

        @Override
        public void onClick(View view) {

            int itemPosition = getAdapterPosition();
           GetPosts intentpost   = dataList.get(itemPosition);
            Intent intent = new Intent(view.getContext(), UpdatePostActivity.class);
            intent.putExtra("postTitleDetails",intentpost.getTitle());
            intent.putExtra("postBodyDetails",intentpost.getBody());
            intent.putExtra("postIdDetails",intentpost.getId().toString());
            view.getContext().startActivity(intent);

        }
    }



    public PostAdapter (List<GetPosts> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        viewHolder.Title.setText(dataList.get(i).getTitle());
        viewHolder.body.setText(dataList.get(i).getBody());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }




}
