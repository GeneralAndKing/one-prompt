package one.prompt.common.model.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

/**
 * 2023/4/2 15:39:19
 *
 * @author yue
 */
public class WechatException extends ResponseStatusException {
  public WechatException(HttpStatusCode status) {
    super(status);
  }

  public WechatException(HttpStatusCode status, String reason) {
    super(status, reason);
  }

  public WechatException(int rawStatusCode, String reason, Throwable cause) {
    super(rawStatusCode, reason, cause);
  }

  public WechatException(HttpStatusCode status, String reason, Throwable cause) {
    super(status, reason, cause);
  }

  protected WechatException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
    super(status, reason, cause, messageDetailCode, messageDetailArguments);
  }
}
