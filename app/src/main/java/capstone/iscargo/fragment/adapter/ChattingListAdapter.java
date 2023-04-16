package capstone.iscargo.fragment.adapter;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.ChattingEntity;

public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public TextView date;
        public TextView unreadCount;

        public ViewHolder(View view) {
            super(view);

            title = view.findViewById(R.id.chattingTitle);
            content = view.findViewById(R.id.chattingContent);
            date = view.findViewById(R.id.chattingDate);
            unreadCount = view.findViewById(R.id.chattingUnreadCount);

            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                    ((Activity)view.getContext())
                            .getMenuInflater()
                            .inflate(R.menu.context_chatting, menu);
                }
            });
        }
    }

    private ChattingEntity entities[];

    public ChattingListAdapter(ChattingEntity[] entities) {
        this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_adapter_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(entities[position].getTitle());
        holder.content.setText(entities[position].getContent());
        holder.date.setText(entities[position].getDate());
        holder.unreadCount.setText(String.valueOf(entities[position].getUnreadCount()));

        if (entities[position].getUnreadCount() == 0)
            holder.unreadCount.setVisibility(TextView.INVISIBLE);
        else if (holder.unreadCount.getVisibility() == TextView.INVISIBLE)
            holder.unreadCount.setVisibility(TextView.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }
}
