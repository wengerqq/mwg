package com.ruoyi.activiti.domain;

import lombok.Data;

/**
 * 流程实例业务关系表
 */
@Data
public class InstanceBusiness {

    private Long id;

    private String instanceId;

    private String businessKey;

    private String module;

}
