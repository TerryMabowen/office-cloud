package cn.mbw.oc.common.throwable;

import lombok.Data;

/**
 * TODO
 *
 * @author Mabowen
 * @date 2020-04-09 09:46
 */
public class OCException extends RuntimeException{
    private static final long serialVersionUID = -8172912212028702721L;

    private String message;

    public OCException(String message) {
        this.message = message;
    }

    public OCException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
