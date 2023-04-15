package capstone.iscargo.fragment.entity;

public class EntityFriends {
    private String profileImage;
    private String name;
    private String company;
    private String phoneNumber;

    public EntityFriends(String name, String company, String phoneNumber) {
        this("", name, company, phoneNumber);
    }

    public EntityFriends(String profileImage, String name, String company, String phoneNumber) {
        this.profileImage = profileImage;
        this.name = name;
        this.company = company;
        this.phoneNumber = phoneNumber;
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
}
