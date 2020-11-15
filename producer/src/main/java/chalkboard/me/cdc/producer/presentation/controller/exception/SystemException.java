package chalkboard.me.cdc.producer.presentation.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SystemException extends RuntimeException {
  private String msg;
}
