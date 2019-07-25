package com.handinglian.extension.param;

import lombok.Data;

import java.util.List;

@Data
public class PushCallRecordParam {
    private List<CallRecordCreateParam> cdr;
}
