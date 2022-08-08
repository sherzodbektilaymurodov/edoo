package auf.group.edu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyExeption extends RuntimeException {
    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public MyExeption(String message) {
        super(message);
    }
}
