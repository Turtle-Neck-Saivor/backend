package com.tukorea.turtleneck.backend.domain.health.exception;

import com.tukorea.turtleneck.backend.global.error.exception.BusinessException;
import com.tukorea.turtleneck.backend.global.error.exception.ErrorCode;

public class NotFoundHealthInfoException extends BusinessException {
    public NotFoundHealthInfoException() {
        super(ErrorCode.NOT_FOUND_MEMBER_ENTITY);
    }
}
