package com.hebo.authDemo.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName HebrException
 * @Author hebo
 * @Date 2022/6/19 17:29
 **/
public class UsernameAndPasswordException extends AuthenticationException {



    /**
     * Constructs a <code>UsernameNotFoundException</code> with the specified message.
     * @param msg the detail message.
     */
    public UsernameAndPasswordException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code UsernameNotFoundException} with the specified message and root
     * cause.
     * @param msg the detail message.
     * @param cause root cause
     */
    public UsernameAndPasswordException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
