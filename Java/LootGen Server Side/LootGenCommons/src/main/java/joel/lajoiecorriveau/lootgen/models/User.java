//package joel.lajoiecorriveau.lootgen.models;
package joel.lajoiecorriveau.lootgen.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 9/8/2015.
 */
public class User {
    private Long ID;

    private String Login;

    private String Password;

    private String Email;

    private List<Long> CustomItems;

    private List<Long> CustomPools;

    private List<String> LootHistory;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public List<Long> getCustomItems() {
        return CustomItems;
    }

    public void setCustomItems(List<Long> customItems) {
        CustomItems = customItems;
    }

    public List<Long> getCustomPools() {
        return CustomPools;
    }

    public void setCustomPools(List<Long> customPools) {
        CustomPools = customPools;
    }

    public List<String> getLootHistory() {
        return LootHistory;
    }

    public void setLootHistory(List<String> lootHistory) {
        LootHistory = lootHistory;
    }

    public User(String pLogin, String pPassword, String pEmail)
    {
        Login = pLogin;
        Password = pPassword;
        Email = pEmail;
        CustomItems = new ArrayList<Long>();
        CustomPools = new ArrayList<Long>();
        LootHistory = new ArrayList<String>();
    }
}
