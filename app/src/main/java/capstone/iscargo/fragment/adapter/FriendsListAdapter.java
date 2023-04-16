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
import capstone.iscargo.fragment.entity.FriendsEntity;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> implements Filterable {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView company;
        public TextView phoneNumber;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.friendsName);
            company = view.findViewById(R.id.friendsCompany);
            phoneNumber = view.findViewById(R.id.friendsPhoneNumber);

            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                    ((Activity)view.getContext())
                            .getMenuInflater()
                            .inflate(R.menu.context_friends, menu);
                }
            });
        }
    }

    private ArrayList<FriendsEntity> searchedEntities;
    private ArrayList<FriendsEntity> groupEntities;
    private ArrayList<FriendsEntity> entities;

    public FriendsListAdapter(ArrayList<FriendsEntity> entities) {
        this.searchedEntities = this.groupEntities = this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_adapter_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(searchedEntities.get(position).getName());
        holder.company.setText(searchedEntities.get(position).getCompany());
        holder.phoneNumber.setText(searchedEntities.get(position).getPhoneNumber());
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
                    searchedEntities = groupEntities;
                else {
                    ArrayList<FriendsEntity> filteringList = new ArrayList<>();

                    for (FriendsEntity entity: groupEntities)
                        if (entity.getName().toLowerCase().contains(charString.toLowerCase()))
                            filteringList.add(entity);

                    searchedEntities = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchedEntities;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                searchedEntities = (ArrayList<FriendsEntity>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void selectGroup(String groupTitle, CharSequence constraint) {
        if (groupTitle.equals("전체"))
            groupEntities = entities;
        else {
            ArrayList<FriendsEntity> groupMembers = new ArrayList<>();

            for (FriendsEntity entity : entities)
                if (entity.getGroupTitle().equals(groupTitle))
                    groupMembers.add(entity);

            groupEntities = groupMembers;
        }
        getFilter().filter(constraint);
    }
}
