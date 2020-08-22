package proxy.entity;

import java.util.Objects;

/**
 * @Author: Jeremy
 * @Date: 2020/8/22 17:43
 */
public class User {
    private Long id;
    private String username;
    private String account;
    private String password;
    private String address;
    // ... Omitting other properties

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("id: %d, username: %s, account: %s, address: %s",
                id, username, account, address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getAccount(), getPassword(), getAddress());
    }
}
