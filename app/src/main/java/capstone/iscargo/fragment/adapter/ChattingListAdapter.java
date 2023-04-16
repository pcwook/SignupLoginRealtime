package capstone.iscargo.fragment.adapter;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.ChattingEntity;

public class ChattingListAdapter extends RecyclerView.Adapter<ChattingListAdapter.ViewHolder> implements Filterable {
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

    private ArrayList<ChattingEntity> searchedEntities;
    private ArrayList<ChattingEntity> entities;

    public ChattingListAdapter(ArrayList<ChattingEntity> entities) {
        this.searchedEntities = this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_adapter_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(searchedEntities.get(position).getTitle());
        holder.content.setText(searchedEntities.get(position).getContent());
        holder.date.setText(searchedEntities.get(position).getDate());
        holder.unreadCount.setText(String.valueOf(searchedEntities.get(position).getUnreadCount()));

        if (searchedEntities.get(position).getUnreadCount() == 0)
            holder.unreadCount.setVisibility(TextView.INVISIBLE);
        else if (holder.unreadCount.getVisibility() == TextView.INVISIBLE)
            holder.unreadCount.setVisibility(TextView.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return searchedEntities.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence contraint) {
                String charString = contraint.toString();

                if (charString.isEmpty())
                    searchedEntities = entities;
                else {
                    ArrayList<ChattingEntity> filteringList = new ArrayList<>();

                    for (ChattingEntity entity: entities)
                        if (entity.getTitle().toLowerCase().contains(charString.toLowerCase()))
                            filteringList.add(entity);

                    searchedEntities = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchedEntities;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                searchedEntities = (ArrayList<ChattingEntity>)results.values;
                notifyDataSetChanged();
            }
        };
    }
}
