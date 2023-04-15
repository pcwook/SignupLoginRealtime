package capstone.iscargo.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.adapter.view_holder.ViewHolderChatting;
import capstone.iscargo.fragment.entity.EntityChatting;

public class AdapterChatting extends RecyclerView.Adapter<ViewHolderChatting> {
    private EntityChatting entities[];
    private LayoutInflater layoutInflater;

    public AdapterChatting(Context context, EntityChatting[] entities) {
        this.layoutInflater = LayoutInflater.from(context);
        this.entities = entities;
    }

    @Override
    public ViewHolderChatting onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_chatting, parent, false);
        return new ViewHolderChatting(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderChatting holder, int position) {
        holder.getTitle().setText(entities[position].getTitle());
        holder.getContent().setText(entities[position].getContent());
        holder.getDate().setText(entities[position].getDate());
        holder.getUnreadCount().setText(String.valueOf(entities[position].getUnreadCount()));

        if (entities[position].getUnreadCount() == 0)
            holder.getUnreadCount().setVisibility(TextView.INVISIBLE);
        else
            if (holder.getUnreadCount().getVisibility() == TextView.INVISIBLE)
                holder.getUnreadCount().setVisibility(TextView.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }
}
