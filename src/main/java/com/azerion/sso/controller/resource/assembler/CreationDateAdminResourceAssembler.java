package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.admin.AdminTodoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CreationDateAdminResourceAssembler extends CreationDateResourceAssembler{

    @Autowired
    public CreationDateAdminResourceAssembler(AdminTodoController controller) {
        super(controller);
    }

}
