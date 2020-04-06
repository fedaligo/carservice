package com.htp.controller.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersMultipleIdsSearchRequest {
    private List<Long> userIds = new ArrayList<>();
}