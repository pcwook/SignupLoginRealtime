package capstone.iscargo.fragment.adapter;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.FriendsEntity;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
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

    private FriendsEntity entities[];

    public FriendsListAdapter(FriendsEntity[] entities) {
        this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_adapter_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(entities[position].getName());
        holder.company.setText(entities[position].getCompany());
        holder.phoneNumber.setText(entities[position].getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }
}
