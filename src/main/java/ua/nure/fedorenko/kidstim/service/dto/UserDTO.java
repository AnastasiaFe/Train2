package ua.nure.fedorenko.kidstim.service.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private String id;
    private String name;
    private String surname;
    private String photo;
    private String email;
    private String password;
    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (getPhoto() != null ? !getPhoto().equals(userDTO.getPhoto()) : userDTO.getPhoto() != null) return false;
        if (!getEmail().equals(userDTO.getEmail())) return false;
        if (!getPassword().equals(userDTO.getPassword())) return false;
        if (getName() != null ? !getName().equals(userDTO.getName()) : userDTO.getName() != null) return false;
        return getSurname() != null ? getSurname().equals(userDTO.getSurname()) : userDTO.getSurname() == null;
    }

    @Override
    public int hashCode() {
        int result = getPhoto() != null ? getPhoto().hashCode() : 0;
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }
}
