package com.project.pairdesksystem.buinesslayer.Invoice;

import com.project.pairdesksystem.datalayer.Invoice.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceDTO;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService{
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice getInvoicesByUserId(int userId) {
        return invoiceRepository.findByUserId(userId);
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceByInvoiceId(int invoiceId) throws NotFoundException {
        return invoiceRepository.findByInvoiceId(invoiceId);
    }

    @Override
    public InvoiceDTO getInvoicesDTOByUserId(int userId) {
        return invoiceMapper.invoiceToInvoiceDTO(getInvoicesByUserId(userId));
    }

    @Override
    public List<InvoiceDTO> getAllInvoiceDTO() {
        return invoiceMapper.invoiceListToInvoiceDTOList(getAllInvoices());
    }

    @Override
    public InvoiceDTO getInvoiceDTOByInvoiceId(int invoiceId) throws NotFoundException {
        return invoiceMapper.invoiceToInvoiceDTO(getInvoiceByInvoiceId(invoiceId));
    }

    @Override
    public InvoiceDTO createInvoiceDTO(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.invoiceDTOToInvoice(invoiceDTO);
        return invoiceMapper.invoiceToInvoiceDTO(createInvoice(invoice));
    }
}
