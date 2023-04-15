package capstone.iscargo;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Friends extends Fragment {
    public class Entity {
        String profileImage;
        String name;
        String company;
        String phoneNumber;

        public Entity(String name, String company, String phoneNumber) {
            this("", name, company, phoneNumber);
        }

        public Entity(String profileImage, String name, String company, String phoneNumber) {
            this.profileImage = profileImage;
            this.name = name;
            this.company = company;
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.friends, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.friendsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new FriendsAdapter(view.getContext(), getEntities()));

        return view;
    }

    // 테스트용 데이터 생성 함수
    private Entity[] getEntities() {
        final int SIZE = 100;

        Entity entities[] = new Entity[SIZE];
        String members[] = {"지상원", "이성민", "강형준", "박찬욱", "이수환"};

        for (int row = 0, width = members.length; row < SIZE / width; row++)
            for (int index = 0, column = row * width; index < 5; index++, column++) {
                String company = column % 2 == 0 ? "영진전문대학교" : "YJ University";
                String phoneNumber = column % 2 == 0 ? "010-1234-5678" : "010-8765-4321";

                entities[column] = new Entity(members[index], company, phoneNumber);
            }

        return entities;
    }
}