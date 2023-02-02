package data;

import java.util.Objects;

public class UserData {
    private final String login;
    private final String password;
    private final String status;

    public UserData(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserRegistrationData{" +
                "login='" + login + "'" +
                ", password='" + password + "'" +
                ", status='" + status + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData that = (UserData) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(status, that.status);
    }
}
