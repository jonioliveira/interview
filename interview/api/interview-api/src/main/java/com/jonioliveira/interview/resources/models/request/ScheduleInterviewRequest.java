package com.jonioliveira.interview.resources.models.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(name="Update slot status Request Model", description="Model of request body to update a slot")
public class ScheduleInterviewRequest {

   @NotNull
   @Schema(description = "the id of slot", example = "1")
   private long slotId;

   @NotNull
   @Schema(description = "the id of candidate", example = "1")
   private int candidateId;

   public long getSlotId() {
      return slotId;
   }

   public void setSlotId(long slotId) {
      this.slotId = slotId;
   }

   public int getCandidateId() {
      return candidateId;
   }

   public void setCandidateId(int candidateId) {
      this.candidateId = candidateId;
   }
}
