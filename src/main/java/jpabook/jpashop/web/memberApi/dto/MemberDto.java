package jpabook.jpashop.web.memberApi.dto;

import jpabook.jpashop.domain.entity.Address;
import lombok.Data;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;

@Data
public class MemberDto {
    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    public MemberDto(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}