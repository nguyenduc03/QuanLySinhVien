package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    @NotNull
    @Size(max = 20, message = "user name toi da 20 ki tu")
    private String userName;
    @NotNull
    @Size(max = 15, message = "Password toi da 15 ki tu")
    private String password;
}
