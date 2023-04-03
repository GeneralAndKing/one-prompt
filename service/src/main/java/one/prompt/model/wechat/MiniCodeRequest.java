package one.prompt.model.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 2023/4/2 15:43:57
 *
 * @param scene      最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
 * @param page       默认是主页，页面 page，例如 pages/index/index，根路径前不要填加 /，不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面。
 * @param checkPath  默认是true，检查page 是否存在，为 true 时 page 必须是已经发布的小程序存在的页面（否则报错）；为 false 时允许小程序未发布或者 page 不存在， 但page 有数量上限（60000个）请勿滥用。
 * @param envVersion 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"。默认是正式版。
 * @param width      默认430，二维码的宽度，单位 px，最小 280px，最大 1280px
 * @author yue
 * @see <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/qrcode-link/qr-code/getUnlimitedQRCode.html">getUnlimitedQRCode</a>
 */
public record MiniCodeRequest(
    String scene,
    String page,
    @JsonProperty("check_path")
    Boolean checkPath,
    @JsonProperty("env_version")
    String envVersion,
    Integer width
) {
}
