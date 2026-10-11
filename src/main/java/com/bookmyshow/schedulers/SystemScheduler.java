package com.bookmyshow.schedulers;

import com.bookmyshow.services.CompanyService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SystemScheduler {

    private CompanyService companyService;

    public SystemScheduler(CompanyService companyService){
        this.companyService = companyService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void executeOnce() {
        companyService.registerInternalCompany();
    }

}
