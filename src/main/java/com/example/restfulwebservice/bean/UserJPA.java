package com.example.restfulwebservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"ssn" , "password"})
@Schema(description = "JPA에서 사용하는 사용자 정보")
@Entity
@Table(name = "Users")
public class UserJPA{

    @Schema(title = "사용자 ID" ,description = "사용자 ID는 자동 생성한다.")
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2 , message = "두글자 이상 선택 해주세요")
    private String name;
    @Past(message = "과거날짜만 선택 가능합니다.")
    private Date joinDate;

    private String password;
    private String ssn;

}
