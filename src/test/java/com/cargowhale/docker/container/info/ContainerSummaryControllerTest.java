package com.cargowhale.docker.container.info;

import com.cargowhale.docker.container.StateFilters;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import com.cargowhale.docker.container.info.model.ContainerSummaryIndex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContainerSummaryControllerTest {

    @InjectMocks
    private ContainerInfoController controller;

    @Mock
    private ContainerInfoService service;

    @Test
    public void getAllContainersReturnsEveryContainerFromService() {
        ContainerSummaryIndex containerSummaryIndex = mock(ContainerSummaryIndex.class);

        when(this.service.getAllContainers()).thenReturn(containerSummaryIndex);

        assertThat(this.controller.getAllContainers(), is(containerSummaryIndex));
    }

    @Test
    public void getContainersFilterByStatusReturnsFilteredContainersFromService() {
        ContainerSummaryIndex containerSummaryIndex = mock(ContainerSummaryIndex.class);
        StateFilters stateFilters = mock(StateFilters.class);

        when(this.service.getContainersFilterByStatus(stateFilters)).thenReturn(containerSummaryIndex);

        assertThat(this.controller.getContainersFilterByStatus(stateFilters), is(containerSummaryIndex));
    }

    @Test
    public void getContainerDetailsById() throws Exception {
        String containerId = "container id!";
        ContainerDetails containerDetails = mock(ContainerDetails.class);

        when(this.service.getContainerDetailsById(containerId)).thenReturn(containerDetails);

        assertThat(this.controller.getContainerById(containerId), is(containerDetails));
    }
}