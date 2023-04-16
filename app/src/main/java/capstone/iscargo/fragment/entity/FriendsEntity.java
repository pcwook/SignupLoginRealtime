package capstone.iscargo.fragment.entity;

public class FriendsEntity {
    private String profileImage;
    private String name;
    private String company;
    private String phoneNumber;
    private String groupTitle;

    public FriendsEntity(String name, String company, String phoneNumber, String groupTitle) {
        this("", name, company, phoneNumber, groupTitle);
    }

    public FriendsEntity(String profileImage, String name, String company, String phoneNumber, String groupTitle) {
        this.profileImage = profileImage;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.groupTitle = groupTitle;
    }

    public String getProfileImage() {
        return profileImage;
    }
    public String getName() {
        return name;
    }
    public String getCompany() {
        return company;
    }
    public String getPhoneNumber() {
        return phoneNumber;
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
    public void setCompany(String company) {
        this.company = company;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}
