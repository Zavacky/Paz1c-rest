package entity;

import java.io.Serial;
import java.io.Serializable;

public class EntityNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -7677918093485960956L;

    public EntityNotFoundException(String message) {
        super(message);
    }
}

