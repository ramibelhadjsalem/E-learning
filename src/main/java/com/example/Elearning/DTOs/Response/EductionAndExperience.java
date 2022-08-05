package com.example.Elearning.DTOs.Response;

import com.example.Elearning.DTOs.Views.View;
import com.example.Elearning.Models.UserModel.Education;
import com.example.Elearning.Models.UserModel.Experience;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EductionAndExperience {
    @JsonView(View.base.class)
    private List<Education> educations;
    @JsonView(View.base.class)
    private List<Experience> experiences ;
}
