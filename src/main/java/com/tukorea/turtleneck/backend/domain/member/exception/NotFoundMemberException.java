package com.tukorea.turtleneck.backend.domain.member.exception;

import com.tukorea.turtleneck.backend.global.error.exception.BusinessException;
import com.tukorea.turtleneck.backend.global.error.exception.ErrorCode;

public class NotFoundMemberException extends BusinessException {
    public NotFoundMemberException() {
        super(ErrorCode.NOT_FOUND_MEMBER_ENTITY);
    }
}
