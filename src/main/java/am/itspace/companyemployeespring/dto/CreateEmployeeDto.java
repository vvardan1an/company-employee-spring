package am.itspace.companyemployeespring.dto;

import am.itspace.companyemployeespring.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeDto {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private double salary;
    private Position position;
    private String profilePic;
    private int companyId;
}
