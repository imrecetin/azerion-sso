package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.TodoController;
import com.azerion.sso.controller.admin.AdminTodoController;
import com.azerion.sso.controller.m2m.M2MTodoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TodoM2MResourceAssembler extends TodoResourceAssembler {

    @Autowired
    public TodoM2MResourceAssembler(CreationDateM2MResourceAssembler creationDateModelAssembler) {
        super(new M2MTodoController(),creationDateModelAssembler);
    }

}
