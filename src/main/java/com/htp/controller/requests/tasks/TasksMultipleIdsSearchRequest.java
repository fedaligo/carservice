package com.htp.controller.requests.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksMultipleIdsSearchRequest {
    private List<Long> ids = new ArrayList<>();
}
