package com.example.qlsv.Models;

import com.example.qlsv.utils.Contants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotNull(message = Contants.emptyUserName)
    @Size(max = 20, message = Contants.overSizeUserName)
    private String userName;
    @NotNull(message = Contants.emptyPassword)
    @Size(max = 15, message = Contants.overSizePassword)
    private String password;
}
