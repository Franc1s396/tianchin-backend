package org.francis.tianchin.common.exception.user;

import org.francis.tianchin.common.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author tianchin
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
