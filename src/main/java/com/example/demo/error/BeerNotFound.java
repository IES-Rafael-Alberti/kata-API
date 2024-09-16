package error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BeerNotFound extends RuntimeException {

    private static final long serialVersionUID = 43876691117560211L;


    public BeerNotFound(Long id) {
        super("No se puede encontrar la cerveza con la ID: " + id);
    }
}



