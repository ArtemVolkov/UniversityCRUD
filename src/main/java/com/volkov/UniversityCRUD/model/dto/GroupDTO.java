package com.volkov.UniversityCRUD.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class GroupDTO {

    private String groupName;
    private String groupCode;
}
