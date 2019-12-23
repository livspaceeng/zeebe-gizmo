package com.livspace.zeebe.gizmo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResolveIncidentDTO {

  private Long incidentKey;
  private Long jobKey;
  private Integer remainingRetries;
}
