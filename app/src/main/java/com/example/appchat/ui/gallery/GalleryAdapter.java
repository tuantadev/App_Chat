package com.example.appchat.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appchat.GlideApp;
import com.example.appchat.R;
import com.example.appchat.model.ImageData;

import java.io.File;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageHolder> {
    private IGalleryAdapter inter;

    public GalleryAdapter(IGalleryAdapter inter){
        this.inter = inter;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_squareimageview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageHolder holder, int position) {
        ImageData data = inter.getData(position);
        if (data.getPath() == null || data.getPath().equals("")){
            GlideApp.with(holder.itemView)
                    .load(R.drawable.default_ava)
                    .into(holder.iv);
        }else {
            GlideApp.with(holder.iv)
                    .load(new File(data.getPath()))
                    .error(R.drawable.default_ava)
                    .placeholder(R.drawable.default_ava)
                    .into(holder.iv);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }

    public interface IGalleryAdapter{
        int getCount();
        ImageData getData(int pos);
        void onClick(int position);
    }

    static class ImageHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_img);
        }
    }
}
