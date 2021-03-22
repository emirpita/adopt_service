package ba.unsa.etf.nwt.adopt_service.responses;

public class ResponseMessage {
    private boolean success;
    private String message;
    private String status;

    public ResponseMessage(boolean success, String message, String status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
