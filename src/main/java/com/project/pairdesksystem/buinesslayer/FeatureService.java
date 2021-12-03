package com.project.pairdesksystem.buinesslayer;

import com.project.pairdesksystem.datalayer.Feature;

import java.util.List;

public interface FeatureService {
    List<Feature> getAllFeatures();
    Feature createFeature(Feature feature);
}
