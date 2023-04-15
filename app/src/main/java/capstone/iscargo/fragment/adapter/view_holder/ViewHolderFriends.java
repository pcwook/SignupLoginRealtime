package capstone.iscargo.fragment.adapter.view_holder;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;

public class ViewHolderFriends extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView company;
    private TextView phoneNumber;

    public ViewHolderFriends(View view) {
        super(view);

        name = view.findViewById(R.id.name);
        company = view.findViewById(R.id.company);
        phoneNumber = view.findViewById(R.id.phoneNumber);

        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                ((Activity)view.getContext()).getMenuInflater().inflate(R.menu.context_friends, menu);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TextView getName() {
        return name;
    }
    public TextView getCompany() {
        return company;
    }
    public TextView getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(TextView name) {
        this.name = name;
    }
    public void setCompany(TextView company) {
        this.company = company;
    }
    public void setPhoneNumber(TextView phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
