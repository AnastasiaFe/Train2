package ua.nure.fedorenko.kidstim.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "child")
public class Child extends ApplicationUser implements Serializable {

    @Column(name = "dateOfBirth")
    private long dateOfBirth;

    @Column(name = "gender")
    private int gender;

    @Column(name = "points")
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
        if (!(o instanceof Child)) return false;
        if (!super.equals(o)) return false;
        Child child = (Child) o;
        return getDateOfBirth() == child.getDateOfBirth() && getGender() == child.getGender() && getPoints() == child.getPoints();
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
