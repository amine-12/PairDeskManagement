#!/usr/bin/env bash

spring init \
--boot-version=2.3.2.RELEASE \
--build=gradle \
--java-version=1.8 \
--packaging=jar \
--name=pairdesksystem \
--package-name=com.project.pairdesksystem \
--groupId=com.project \
--dependencies=actuator,webflux \
--version=1.0.0-SNAPSHOT \
pairdesksystem
