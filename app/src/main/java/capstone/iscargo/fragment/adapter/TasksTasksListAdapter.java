package capstone.iscargo.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;

public class TasksTasksListAdapter extends RecyclerView.Adapter<TasksTasksListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView content;

        public ViewHolder(View view) {
            super(view);

            content = view.findViewById(R.id.tasksContent);

            Button button1 = view.findViewById(R.id.tasksSelect);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "주세요", Toast.LENGTH_SHORT).show();
                }
            });
            Button button2 = view.findViewById(R.id.tasksMove);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "이동", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private String contents[];

    public TasksTasksListAdapter(String[] contents) {
        this.contents = contents;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_adapter_tasks_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.content.setText(contents[position]);
    }

    @Override
    public int getItemCount() {
        return contents.length;
    }
}
