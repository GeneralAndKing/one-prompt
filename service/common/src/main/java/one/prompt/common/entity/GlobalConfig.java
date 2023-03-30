package one.prompt.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import one.prompt.common.base.BaseEntity;
import one.prompt.common.modal.constant.GlobalConfigKey;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


/**
 * 2023/3/31 00:03:47
 *
 * @author yue
 */
@Getter
@Setter
@Entity
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(name = "global_config")
@Where(clause = "deleted = false")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE global_config SET deleted=true WHERE id=?")
public class GlobalConfig extends BaseEntity<GlobalConfig> {

  /**
   * Global config key.
   */
  @Column(name = "`key`")
  @Enumerated(EnumType.STRING)
  private GlobalConfigKey key;

  /**
   * Global config value.
   */
  @Column(name = "`value`")
  private String value;

  /**
   * Get value.
   *
   * @param defaultValue default if null
   * @return value
   */
  public String getValue(String defaultValue) {
    return Objects.isNull(value) ? defaultValue : value;
  }

  /**
   * Get int type value with default value.
   *
   * @param defaultValue default value
   * @return value
   */
  public Integer getIntValue(GlobalConfigKey defaultValue) {
    return Integer.parseInt(NumberUtils.isDigits(value) ? value : defaultValue.defaultValue());
  }

  /**
   * Get boolean value.
   *
   * @return value
   */
  @JsonIgnore
  public Boolean getBooleanValue() {
    return BooleanUtils.toBoolean(value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    GlobalConfig sysGlobalConfig = (GlobalConfig) o;
    return getId() != null && Objects.equals(getId(), sysGlobalConfig.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}