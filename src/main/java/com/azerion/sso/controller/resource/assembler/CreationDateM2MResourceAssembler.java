package com.azerion.sso.controller.resource.assembler;

import com.azerion.sso.controller.m2m.M2MTodoController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class CreationDateM2MResourceAssembler extends CreationDateResourceAssembler{

    @Autowired
    public CreationDateM2MResourceAssembler(M2MTodoController controller) {
        super(controller);
    }

}
