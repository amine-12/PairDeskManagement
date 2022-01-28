package com.project.pairdesksystem.buinesslayer.Reports;

import com.project.pairdesksystem.buinesslayer.Feature.FeatureService;
import com.project.pairdesksystem.datalayer.Feature.FeaturesRepository;
import com.project.pairdesksystem.presentationlayer.FeatureResource;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@RequestMapping("/reports")
@RestController
public class ReportResource {

    @Autowired
    private FeaturesRepository featuresRepository;

    @Autowired
    private ReportService reportService;

    private static final Logger LOG = LoggerFactory.getLogger(FeatureResource.class);

    @CrossOrigin
    @GetMapping("/report/{format}")
    @PreAuthorize("hasRole('ADMIN')")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }
}
