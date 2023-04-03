package one.prompt.service;

/**
 * 2023/4/2 21:12:33
 *
 * @author yue
 */
public interface WechatService {

  void login(String wechatCode, String authCode, String openId);

}
