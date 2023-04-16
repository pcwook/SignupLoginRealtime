package capstone.iscargo.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.R;
import capstone.iscargo.fragment.listener.FriendsGroupClickListener;

public class FriendsGroupAdapter extends RecyclerView.Adapter<FriendsGroupAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public RadioButton groupTitle;

        public ViewHolder(View view) {
            super(view);

            groupTitle = view.findViewById(R.id.friendsGroupTitle);
        }
    }

    private ArrayList<String> groupTitles;
    private FriendsGroupClickListener friendsGroupClickListener;
    private int selectedGroupPosition;

    public FriendsGroupAdapter(ArrayList<String> groupTitles, FriendsGroupClickListener listener, int selectedGroupPosition) {
        this.groupTitles = groupTitles;
        this.friendsGroupClickListener = listener;
        this.selectedGroupPosition = selectedGroupPosition;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_adapter_group, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.groupTitle.setText(groupTitles.get(position));
        holder.groupTitle.setChecked(position == selectedGroupPosition);
        holder.groupTitle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    selectedGroupPosition = holder.getAdapterPosition();
                    friendsGroupClickListener.onClick(selectedGroupPosition, holder.groupTitle.getText().toString());
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return groupTitles.size();
    }
}
