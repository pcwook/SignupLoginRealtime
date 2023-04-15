package capstone.iscargo.fragment.adapter.view_holder;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;

public class ViewHolderChatting extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView content;
    private TextView date;
    private TextView unreadCount;

    public ViewHolderChatting(View view) {
        super(view);

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        date = view.findViewById(R.id.date);
        unreadCount = view.findViewById(R.id.unreadCount);

        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
                ((Activity)view.getContext()).getMenuInflater().inflate(R.menu.context_chatting, menu);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TextView getTitle() {
        return title;
    }
    public TextView getContent() {
        return content;
    }
    public TextView getDate() {
        return date;
    }
    public TextView getUnreadCount() {
        return unreadCount;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
    public void setContent(TextView content) {
        this.content = content;
    }
    public void setDate(TextView date) {
        this.date = date;
    }
    public void setUnreadCount(TextView unreadCount) {
        this.unreadCount = unreadCount;
    }
}
