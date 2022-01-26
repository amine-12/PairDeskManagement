package com.project.pairdesksystem.presentationlayer;

import com.project.pairdesksystem.buinesslayer.Invoice.InvoiceService;
import com.project.pairdesksystem.datalayer.Feature.FeatureDTO;
import com.project.pairdesksystem.datalayer.Invoice.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceDTO;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/invoices")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class InvoiceResource {
    @Autowired
    private final InvoiceService invoiceService;
    private static final Logger LOG = LoggerFactory.getLogger(FeatureResource.class);

    public InvoiceResource(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @CrossOrigin
    @GetMapping("/api/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<InvoiceDTO> getAllInvoiceDTOList() {
        return invoiceService.getAllInvoiceDTO();
    }

    @CrossOrigin
    @GetMapping("/api/{invoiceId}")
    public InvoiceDTO findInvoice(@PathVariable int invoiceId) throws NotFoundException {
        return invoiceService.getInvoiceDTOByInvoiceId(invoiceId);
    }

    @CrossOrigin
    @GetMapping("/api/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public InvoiceDTO getInvoiceByUserId(@PathVariable int userId) throws NotFoundException {
        return invoiceService.getInvoicesDTOByUserId(userId);
    }

    @CrossOrigin
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/api/add"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public InvoiceDTO createInvoice (@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoiceDTO(invoiceDTO);
    }

    @CrossOrigin
    @GetMapping("/api/user/payout/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public double getTotalInvoicePriceByUserId(@PathVariable int userId) throws NotFoundException {
        return invoiceService.getTotalPriceByUserId(userId);
    }

}
