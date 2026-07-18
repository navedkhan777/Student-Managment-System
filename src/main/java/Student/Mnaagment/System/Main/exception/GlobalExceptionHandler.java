    package Student.Mnaagment.System.Main.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.http.HttpStatusCode;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.FieldError;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.ControllerAdvice;
    import org.springframework.web.bind.annotation.ExceptionHandler;

    import java.lang.reflect.Field;

    @ControllerAdvice

    public class GlobalExceptionHandler {


        @ExceptionHandler(StudentNotFoundException.class)
        public ResponseEntity<?> handleStudentNotFoundException(StudentNotFoundException ex){
           ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        @ExceptionHandler(EmailAlreadyExistsException.class)
        public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException exx){
            ErrorResponse error = new ErrorResponse(exx.getMessage(), HttpStatus.CONFLICT.value());

            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleGlobalException(Exception exxx){
            ErrorResponse error = new ErrorResponse(exxx.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }

        public ResponseEntity<?> handlevalidationExceeption(MethodArgumentNotValidException exxxx){
            FieldError fieldError = exxxx.getBindingResult().getFieldError();

            ErrorResponse error = new ErrorResponse(fieldError.getDefaultMessage(), HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    }
