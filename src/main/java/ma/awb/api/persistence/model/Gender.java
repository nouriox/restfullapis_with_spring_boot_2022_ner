package ma.awb.api.persistence.model;

import java.io.Serializable;

public enum Gender implements Serializable {
    MALE,
    FEMALE,
    OTHER;

    public String getGender() {
        return this.name();
    }

}
