package capstone.iscargo.fragment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import capstone.iscargo.R;
import capstone.iscargo.fragment.entity.TasksEntity;

public class TasksListAdapter extends RecyclerView.Adapter<TasksListAdapter.ViewHolder> implements Filterable {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView userName;
        public TextView date;
        public RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);

            userName = view.findViewById(R.id.tasksUserName);
            date = view.findViewById(R.id.tasksDate);
            recyclerView = view.findViewById(R.id.tasksTasksList);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        }
    }

    private ArrayList<TasksEntity> searchedEntities;
    private ArrayList<TasksEntity> groupEntities;
    private ArrayList<TasksEntity> entities;

    public TasksListAdapter(ArrayList<TasksEntity> entities) {
        this.searchedEntities = this.groupEntities = this.entities = entities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_adapter_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.userName.setText(searchedEntities.get(position).getName());
        holder.date.setText(searchedEntities.get(position).getDate());
        holder.recyclerView.setAdapter(new TasksTasksListAdapter(searchedEntities.get(position).getContent()));
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
                    ArrayList<TasksEntity> filteringList = new ArrayList<>();

                    for (TasksEntity entity: groupEntities)
                        if (entity.getName().toLowerCase().contains(charString.toLowerCase()))
                            filteringList.add(entity);
                        else {
                            String contents[] = entity.getContent();

                            for (String content: contents)
                                if (content.toLowerCase().contains(charString.toLowerCase()))
                                    filteringList.add(entity);
                        }

                    searchedEntities = filteringList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = searchedEntities;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                searchedEntities = (ArrayList<TasksEntity>)results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void selectGroup(String groupTitle, CharSequence constraint) {
        if (groupTitle.equals("전체"))
            groupEntities = entities;
        else {
            ArrayList<TasksEntity> groupTasks = new ArrayList<>();

            for (TasksEntity entity: entities)
                if (entity.getGroupTitle().equals(groupTitle))
                    groupTasks.add(entity);

            groupEntities = groupTasks;
        }
        getFilter().filter(constraint);
    }
}
