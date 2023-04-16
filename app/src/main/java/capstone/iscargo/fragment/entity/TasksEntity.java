package capstone.iscargo.fragment.entity;

public class TasksEntity {
    private String profileImage;
    private String name;
    private String date;
    private String content[];
    private String groupTitle;

    public TasksEntity(String name, String date, String[] content, String groupTitle) {
        this("", name, date, content, groupTitle);
    }

    public TasksEntity(String profileImage, String name, String date, String[] content, String groupTitle) {
        this.profileImage = profileImage;
        this.name = name;
        this.date = date;
        this.content = content;
        this.groupTitle = groupTitle;
    }

    public String getProfileImage() {
        return profileImage;
    }
    public String getName() {
        return name;
    }
    public String getDate() {
        return date;
    }
    public String[] getContent() {
        return content;
    }
    public String getGroupTitle() {
        return groupTitle;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setContent(String[] content) {
        this.content = content;
    }
    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}