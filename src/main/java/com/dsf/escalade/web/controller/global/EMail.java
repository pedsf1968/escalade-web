package com.dsf.escalade.web.controller.global;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EMail {
   @NotNull
   private String name;
   @NotNull
   @Email
   private String email;
   @NotNull
   @Min(10)
   private String message;
}
