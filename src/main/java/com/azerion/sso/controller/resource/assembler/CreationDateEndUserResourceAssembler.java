package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.enduser.EndUserTodoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CreationDateEndUserResourceAssembler extends CreationDateResourceAssembler{

    @Autowired
    public CreationDateEndUserResourceAssembler() {
        super(new EndUserTodoController());
    }

}
