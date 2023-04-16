package capstone.iscargo.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.TasksEntity;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView date;

        public ViewHolder(View view) {
            super(view);

            userName = view.findViewById(R.id.tasksUserName);
            date = view.findViewById(R.id.tasksDate);
        }
    }

    private TasksEntity entities[];
    private int index;

    public TasksListAdapter(TasksEntity[] entities) {
        this.entities = entities;
        this.index = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_adapter_list, parent, false);

        RecyclerView recyclerView = view.findViewById(R.id.tasksTasksList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new TasksTasksListAdapter(entities[index++].getContent()));

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userName.setText(entities[position].getName());
        holder.date.setText(entities[position].getDate());
    }

    @Override
    public int getItemCount() {
        return entities.length;
    }
}
