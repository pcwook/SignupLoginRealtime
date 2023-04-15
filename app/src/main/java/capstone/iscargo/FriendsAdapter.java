package capstone.iscargo;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView company;
        public TextView phoneNumber;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            company = view.findViewById(R.id.company);
            phoneNumber = view.findViewById(R.id.phoneNumber);

            view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                    ((Activity)view.getContext()).getMenuInflater().inflate(R.menu.friends_context_menu, menu);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, getAdapterPosition());
                    Toast.makeText(view.getContext(), "Test", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private Friends.Entity entities[];
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public FriendsAdapter(Context context, Friends.Entity[] entities) {
        this.layoutInflater = LayoutInflater.from(context);
        this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.friends_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(entities[position].name);
        holder.company.setText(entities[position].company);
        holder.phoneNumber.setText(entities[position].phoneNumber);
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
