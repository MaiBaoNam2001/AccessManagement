package com.citynow.accessmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "role_authority")
public class RoleAuthority {

  @Id
  @Column(name = "id", nullable = false)
  @Type(type = "org.hibernate.type.TextType")
  private String id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_code")
  private Role roleCode;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "authority_code")
  private Authority authorityCode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Role getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(Role roleCode) {
    this.roleCode = roleCode;
  }

  public Authority getAuthorityCode() {
    return authorityCode;
  }

  public void setAuthorityCode(Authority authorityCode) {
    this.authorityCode = authorityCode;
  }

}