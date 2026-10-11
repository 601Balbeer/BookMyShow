package com.bookmyshow.transformers;

import com.bookmyshow.constants.SystemConstant;
import com.bookmyshow.enums.CompanyType;
import com.bookmyshow.enums.UserType;
import com.bookmyshow.models.Company;
import com.bookmyshow.models.Employee;
import com.bookmyshow.models.User;
import com.bookmyshow.utilities.SystemUtility;

import java.time.LocalDateTime;

public class UserTransformer {

    public static Employee transformCompanyToAdminUser(Company company){
        return Employee.builder()
                .email(company.getEmail())
                .userType(company.getCompanyType() == CompanyType.INTERNAL ? UserType.INTERNAL : UserType.EMPLOYEE)
                .designation("admin")
                .company(company)
                .employeeCode(SystemUtility.generate("EMP"))
                .email(company.getEmail())
                .fullName(company.getCompanyName() + " " + SystemConstant.DEFAULT_ADMIN_NAME)
                .mobileNumber(company.getPhoneNumber())
                .passwordHash(SystemUtility.generateRandomPassword(SystemConstant.DEFAULT_PASSWORD_LENGTH))
                .lastLoginAt(null)
                .createdBy("system")
                .updatedBy("system")
                .build();
    }

}
