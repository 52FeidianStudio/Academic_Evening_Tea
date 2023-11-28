package com.ruoyi.common.exception.user;

import com.ruoyi.common.exception.base.BaseException;

public class TokenExpiredException extends BaseException {

    private static final long serialVersionUID = 1L;


    public TokenExpiredException(String module, String code, Object[] args, String defaultMessage) {
        super(module, code, args, defaultMessage);
    }

    public TokenExpiredException(String module, String code, Object[] args) {
        super(module, code, args);
    }

    public TokenExpiredException(String module, String defaultMessage) {
        super(module, defaultMessage);
    }

    public TokenExpiredException(String code, Object[] args) {
        super(code, args);
    }

    public TokenExpiredException(String defaultMessage) {
        super(defaultMessage);
    }
}
