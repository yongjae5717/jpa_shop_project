package jpabook.jpashop.web.memberApi.dto;

import jpabook.jpashop.domain.entity.Address;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateMemberRequest{
    @NotEmpty
    private String name;

    @Embedded
    private Address address;
}