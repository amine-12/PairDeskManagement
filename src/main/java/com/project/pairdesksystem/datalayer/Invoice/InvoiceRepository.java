package com.project.pairdesksystem.datalayer.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findByUserId(int userId);
    Invoice findByInvoiceId(int invoiceId);
}
