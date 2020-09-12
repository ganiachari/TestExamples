package com.testexamples.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.testexamples.R;
import com.testexamples.models.Images;

import java.util.List;



public class AdapterImageList extends RecyclerView.Adapter<AdapterImageList.MyviewHolder> {
    private static final String TAG = "ActivityImagesList";

    Context context;
    List<Images> imagesList;


    public AdapterImageList(Context context, List<Images> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
        notifyDataSetChanged();
    }

    @Override
    public AdapterImageList.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter, parent, false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterImageList.MyviewHolder holder, final int position) {
        holder.textAuthorName.setText(imagesList.get(position).getAuthor().toString());
        Picasso.get().load(imagesList.get(position).download_url).resize(400, 400).centerCrop().into(holder.imageAuthor);
        holder.linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + imagesList.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext(), R.style.MyAlertDialogStyle);
                builder.setMessage(imagesList.get(position).author);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (imagesList != null) {
            return imagesList.size();
        }
        return 0;

    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView textAuthorName;
        ImageView imageAuthor;
        LinearLayout linearlayout;

        public MyviewHolder(View itemView) {
            super(itemView);
            textAuthorName = (TextView) itemView.findViewById(R.id.textViewAuthor);
            imageAuthor = (ImageView) itemView.findViewById(R.id.imageViewAuthor);
            linearlayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
