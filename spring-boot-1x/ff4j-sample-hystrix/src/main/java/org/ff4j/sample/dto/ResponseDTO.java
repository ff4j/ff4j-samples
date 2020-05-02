package org.ff4j.sample.dto;

public class ResponseDTO {
    
    private String message;
    
    public ResponseDTO(String msg) {
        this.message = msg;
    }

    /**
     * Getter accessor for attribute 'message'.
     *
     * @return
     *       current value of 'message'
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter accessor for attribute 'message'.
     * @param message
     * 		new value for 'message '
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    

}
