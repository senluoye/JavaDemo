package com.forsake.myproject.excaption;

/**
 * @ClassName ServiceExcaption
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-09 22:48
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected final String message;

    public ServiceException(String message) {
        this.message = message;
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
