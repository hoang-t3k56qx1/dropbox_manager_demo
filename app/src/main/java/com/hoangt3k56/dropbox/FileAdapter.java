package com.hoangt3k56.dropbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.Metadata;

import java.util.List;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.viewHodel> {

    public ListenerMetadata listenerMetadata;
    private List<Metadata> metadataList;
    ListenerString listenerString;

    public FileAdapter(ListenerString listenerString) {
        this.listenerString = listenerString;
    }

    void setMetadataList(List<Metadata> list) {
        this.metadataList = list;
        notifyDataSetChanged();
     }

    @NonNull
    @Override
    public viewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_item_file, parent, false);
        return new viewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHodel holder, int position) {
       Metadata metadata = metadataList.get(position);

        if (metadata != null) {
            holder.tvName_file.setText(metadata.getName());
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listenerString.listenerString(metadata.getPathLower());
                }
            });

            if (metadata instanceof FileMetadata) {
                holder.img_file.setImageResource(R.drawable.icons8_file);
            }
            else if (metadata instanceof FolderMetadata) {
                holder.img_file.setImageResource(R.drawable.icons8_folder_48);
            }
        }
    }

    @Override
    public int getItemCount() {

        if (metadataList != null) {
            return metadataList.size();
        }
        return 0;
    }


    public class viewHodel extends RecyclerView.ViewHolder {

        private TextView tvName_file;
        private ImageView img_file;
        private LinearLayout layout;

        public viewHodel(@NonNull View itemView) {
            super(itemView);
            tvName_file = (TextView) itemView.findViewById(R.id.tvName_file);
            img_file = (ImageView) itemView.findViewById(R.id.img_item_file);
            layout = (LinearLayout) itemView.findViewById(R.id.item_file);
        }
    }
}
