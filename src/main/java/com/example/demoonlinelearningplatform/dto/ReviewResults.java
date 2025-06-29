package com.example.demoonlinelearningplatform.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ReviewResults {
    private List<Essay> essays = new ArrayList<>();
    private List<MultipleChoice> multipleChoices = new ArrayList<>();
}
