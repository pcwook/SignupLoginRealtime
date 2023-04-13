package capstone.iscargo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private String[] names;
    private String[] companies;
    private LayoutInflater layoutInflater;
    private ItemClickListener itemClickListener;

    public FriendsAdapter(Context context, String[] names, String[] companies) {
        this.layoutInflater = LayoutInflater.from(context);
        this.names = names;
        this.companies = companies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.friends_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(names[position]);
        holder.company.setText(companies[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView company;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            company = view.findViewById(R.id.company);

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

    public String getItem(int id) {
        return names[id];
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
