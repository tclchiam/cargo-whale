package com.cargowhale.docker.client.core;

import com.cargowhale.docker.client.containers.management.state.ContainerChangeState;
import org.springframework.stereotype.Component;

@Component
public class DockerEndpointBuilder {

    private static final String API_VERSION = "/v1.24";
    private static final String CONTAINERS_ENDPOINT = "/containers";
    private static final String JSON = "/json";
    private static final String PROCESSES = "/top";
    private static final String LOGS = "/logs";

    public String getListAllContainersEndpoint() {
        return API_VERSION + CONTAINERS_ENDPOINT + JSON + "?all=1";
    }

    public String getListContainersWithFiltersEndpoint() {
        return API_VERSION + CONTAINERS_ENDPOINT + JSON + "?filters={filters}";
    }

    public String getInspectContainerEndpoint(final String containerId) {
        return API_VERSION + CONTAINERS_ENDPOINT + "/" + containerId + JSON;
    }

    public String getContainerLogsEndpoint(final String containerId) {
        return API_VERSION + CONTAINERS_ENDPOINT + "/" + containerId + LOGS;
    }

    public String getContainerProcessesEndpoint(final String containerId) {
        return API_VERSION + CONTAINERS_ENDPOINT + "/" + containerId + PROCESSES;
    }

    public String getContainerChangeStateEndpoint(final String containerId, final ContainerChangeState state) {
        return API_VERSION + CONTAINERS_ENDPOINT + "/" + containerId + "/" + state.state;
    }
}