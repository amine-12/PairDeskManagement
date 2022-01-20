package com.project.pairdesksystem.datalayer.Invoice;
import com.project.pairdesksystem.datalayer.Feature.Feature;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements(groups = Invoice.class)
    private Integer invoiceId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @UniqueElements(groups = Feature.class)
    private Integer userId;

    @CreationTimestamp
    @Column(name="CREATED_TIME")
    private Date creationTime;

    @Column(name="invoice_pay")
    private Double invoicePay;

    public Integer getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        Random rand = new Random();
        this.invoiceId = rand.nextInt(99999);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Double getInvoicePay() {
        return invoicePay;
    }

    public void setInvoicePay(Double invoicePay) {
        this.invoicePay = invoicePay;
    }
}
