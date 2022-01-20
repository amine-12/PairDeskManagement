package com.project.pairdesksystem.buinesslayer.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.Invoice;
import com.project.pairdesksystem.datalayer.Invoice.InvoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;
@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    @Mappings(
            {
                    @Mapping(target = "id", ignore = true),
            }
    )
    Invoice invoiceDTOToInvoice(InvoiceDTO invoiceDTO);
    InvoiceDTO invoiceToInvoiceDTO(Invoice invoice);


    List<Invoice> invoiceDTOListToInvoiceList(List<InvoiceDTO> invoiceDTOList);

    List<InvoiceDTO> invoiceListToInvoiceDTOList(List<Invoice> invoiceList);
}
