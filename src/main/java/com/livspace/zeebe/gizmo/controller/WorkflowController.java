/** */
package com.livspace.zeebe.gizmo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.livspace.zeebe.gizmo.common.ResolveIncidentDTO;

import io.zeebe.spring.client.ZeebeClientLifecycle;

/** @author Ankit Agrawal */
@RestController
public class WorkflowController {

  @Autowired private ZeebeClientLifecycle zeebeClient;

  @RequestMapping(
      path = "/resolveIncident",
      method = RequestMethod.PUT,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public void resolveIncident(@RequestBody ResolveIncidentDTO resolveIncidentDTO) {

    if (resolveIncidentDTO.getJobKey() != null && resolveIncidentDTO.getJobKey() > 0) {
      zeebeClient
          .newUpdateRetriesCommand(resolveIncidentDTO.getJobKey())
          .retries(resolveIncidentDTO.getRemainingRetries())
          .send()
          .join();
    }
    zeebeClient.newResolveIncidentCommand(resolveIncidentDTO.getIncidentKey()).send().join();
  }

  @RequestMapping(path = "/cancelWorkflowInstance/{key}", method = RequestMethod.DELETE)
  public void cancelWorkflowInstance(@PathVariable("key") Long key) throws Exception {
    zeebeClient.newCancelInstanceCommand(key).send().join();
  }
}
