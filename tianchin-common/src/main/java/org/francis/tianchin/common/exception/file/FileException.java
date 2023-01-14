package org.francis.tianchin.common.exception.file;

import org.francis.tianchin.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author tianchin
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
