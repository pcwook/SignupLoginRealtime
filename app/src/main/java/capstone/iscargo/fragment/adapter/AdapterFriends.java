package capstone.iscargo.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.adapter.view_holder.ViewHolderFriends;
import capstone.iscargo.fragment.entity.EntityFriends;

public class AdapterFriends extends RecyclerView.Adapter<ViewHolderFriends> {
    private EntityFriends entities[];
    private LayoutInflater layoutInflater;

    public AdapterFriends(Context context, EntityFriends[] entities) {
        this.layoutInflater = LayoutInflater.from(context);
        this.entities = entities;
    }

    @Override
    public ViewHolderFriends onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.adapter_friends, parent, false);
        return new ViewHolderFriends(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderFriends holder, int position) {
        holder.getName().setText(entities[position].getName());
        holder.getCompany().setText(entities[position].getCompany());
        holder.getPhoneNumber().setText(entities[position].getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }
}
