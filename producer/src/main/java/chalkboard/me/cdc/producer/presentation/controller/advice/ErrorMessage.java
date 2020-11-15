package chalkboard.me.cdc.producer.presentation.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorMessage implements Serializable {
  private String msg;
}
