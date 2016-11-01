package com.cargowhale.docker.client;

import com.cargowhale.docker.container.ContainerState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DockerContainerFilters {

    private final Set<ContainerState> status;

    public DockerContainerFilters(final ContainerState[] status) {
        this.status = new LinkedHashSet<>(Arrays.asList(status));
    }

    public Set<ContainerState> getStatus() {
        return this.status;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final DockerContainerFilters that = (DockerContainerFilters) o;

        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        return status != null ? status.hashCode() : 0;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
