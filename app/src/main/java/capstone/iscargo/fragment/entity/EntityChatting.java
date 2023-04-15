package capstone.iscargo.fragment.entity;

public class EntityChatting {
    private String chattingImage;
    private String title;
    private String content;
    private String date;
    private int unreadCount;

    public EntityChatting(String title, String content, String date) {
        this(title, content, date, 0);
    }

    public EntityChatting(String title, String content, String date, int unreadCount) {
        this("", title, content, date, unreadCount);
    }

    public EntityChatting(String chattingImage, String title, String content, String date, int unreadCount) {
        this.chattingImage = chattingImage;
        this.title = title;
        this.content = content;
        this.date = date;
        this.unreadCount = unreadCount;
    }

    public String getChattingImage() {
        return chattingImage;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getDate() {
        return date;
    }
    public int getUnreadCount() {
        return unreadCount;
    }

    public void setChattingImage(String chattingImage) {
        this.chattingImage = chattingImage;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setDate(String Date) {
        this.date = date;
    }
    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
}