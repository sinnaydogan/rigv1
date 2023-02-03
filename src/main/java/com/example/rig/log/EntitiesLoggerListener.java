package com.example.rig.log;

import com.example.rig.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

public class EntitiesLoggerListener {
    private static final Logger logger = LoggerFactory.getLogger(EntitiesLoggerListener.class);
    @PrePersist
    public void methodInvokedBeforePersist(Order emp) {
        logger.info("persisting employee with id = " + emp.getId());
//        System.out.println("persisting employee with id = " + emp.getId());
    }

    @PostPersist
    public void methodInvokedAfterPersist(Order emp) {
        System.out.println("Persisted employee with id = " + emp.getId());
    }

    @PreUpdate
    public void methodInvokedBeforeUpdate(Order emp) {
        System.out.println("Updating employee with id = " + emp.getId());
    }

    @PostUpdate
    public void methodInvokedAfterUpdate(Order emp) {
        System.out.println("Updated employee with id = " + emp.getId());
    }

    @PreRemove
    private void methodInvokedBeforeRemove(Order emp) {
        System.out.println("Removing employee with id = " + emp.getId());
    }

    @PostRemove
    public void methodInvokedAfterRemove(Order emp) {
        System.out.println("Removed employee with id = " + emp.getId() );
    }
}
