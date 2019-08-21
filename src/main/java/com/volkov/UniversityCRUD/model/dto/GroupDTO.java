package com.volkov.UniversityCRUD.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupDTO {

    private long id;

    private String groupName;
    private String groupCode;
}
