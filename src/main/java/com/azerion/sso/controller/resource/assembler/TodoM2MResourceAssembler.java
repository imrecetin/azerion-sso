package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.admin.AdminTodoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TodoM2MResourceAssembler extends TodoResourceAssembler {

    @Autowired
    public TodoM2MResourceAssembler(AdminTodoController controller, CreationDateM2MResourceAssembler creationDateModelAssembler) {
        super(controller,creationDateModelAssembler);
    }

}
