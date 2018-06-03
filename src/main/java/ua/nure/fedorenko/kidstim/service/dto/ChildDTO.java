package ua.nure.fedorenko.kidstim.service.dto;

import java.io.Serializable;

public class ChildDTO extends UserDTO implements Serializable {

    private long dateOfBirth;
    private int gender;
    private int points;

    public long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChildDTO)) return false;
        if (!super.equals(o)) return false;

        ChildDTO childDTO = (ChildDTO) o;

        if (getDateOfBirth() != childDTO.getDateOfBirth()) return false;
        if (getGender() != childDTO.getGender()) return false;
        return getPoints() == childDTO.getPoints();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (getDateOfBirth() ^ (getDateOfBirth() >>> 32));
        result = 31 * result + getGender();
        result = 31 * result + getPoints();
        return result;
    }


}
