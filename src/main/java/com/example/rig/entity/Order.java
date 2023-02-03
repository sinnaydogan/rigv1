package com.example.rig.entity;

import com.example.rig.log.EntitiesLoggerListener;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(EntitiesLoggerListener.class)
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_order", allocationSize = 1)
    @GeneratedValue(generator = "seq_order", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private List<Book> bookList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private Date orderDate;



    //Logging
    @PrePersist
    public void methodInvokedBeforePersist() {
        System.out.println("Invoked before persisting order");
    }

    @PostPersist
    public void methodInvokedAfterPersist() {
        System.out.println("Invoked after persisting order");
    }

    @PreUpdate
    public void methodInvokedBeforeUpdate() {
        System.out.println("Invoked before updating order");
    }

    @PostUpdate
    public void methodInvokedAfterUpdate() {
        System.out.println("Invoked after updating order");
    }

    @PreRemove
    public void methodInvokedBeforeRemove() {
        System.out.println("Invoked before removing order");
    }

    @PostRemove
    public void methodInvokedAfterRemove() {
        System.out.println("Invoked after removing order");
    }
}
