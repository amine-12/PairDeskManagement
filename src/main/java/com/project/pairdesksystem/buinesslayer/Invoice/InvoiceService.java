package com.project.pairdesksystem.buinesslayer.Invoice;

import com.project.pairdesksystem.datalayer.Invoice.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceDTO;
import javassist.NotFoundException;

import java.util.List;

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Invoice getInvoicesByUserId(int userId);
    Invoice createInvoice(Invoice invoice);
    Invoice getInvoiceByInvoiceId(int invoiceId) throws NotFoundException;


    InvoiceDTO getInvoicesDTOByUserId(int userId);
    List<InvoiceDTO> getAllInvoiceDTO();
    InvoiceDTO getInvoiceDTOByInvoiceId(int invoiceId) throws NotFoundException;
    InvoiceDTO createInvoiceDTO(InvoiceDTO invoiceDTO);

}
