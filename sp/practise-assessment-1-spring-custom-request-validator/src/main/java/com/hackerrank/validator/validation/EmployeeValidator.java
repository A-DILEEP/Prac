//package com.hackerrank.validator.validation;
//
//import com.hackerrank.validator.model.Employee;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//public class EmployeeValidator implements Validator {
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Employee.class.isAssignableFrom(aClass);
//    }
//
//    @Override
//    public void validate(Object employeeObject, Errors errors) {
//        // write validation logic here
//    }
//}

package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }
 
    @Override
    public void validate(Object employeeObject, Errors errors) {
        Employee employee = (Employee) employeeObject;

        if (employee.getFullName() == null || employee.getFullName().trim().isEmpty()) {
            errors.rejectValue("fullName", "fullName.mandatory", "The fullName is a mandatory field");
        }

        if (employee.getMobileNumber() == null || employee.getMobileNumber() == 0L) {
            errors.rejectValue("mobileNumber", "mobileNumber.mandatory", "The mobileNumber is a mandatory field");
        } else {
            String mobile = employee.getMobileNumber().toString();
            if (!mobile.matches("\\d{10}")) {
                errors.rejectValue("mobileNumber", "mobileNumber.mandatory", "The mobileNumber is a mandatory field");
            }
        }
        if (employee.getEmailId() == null || employee.getEmailId().trim().isEmpty()) {
            errors.rejectValue("emailId", "emailId.mandatory", "The emailId is a mandatory field");
        } else if (!employee.getEmailId().contains("@")) {
            errors.rejectValue("emailId", "emailId.invalid", "The emailId should be in a valid email format");
        }

        if (employee.getDateOfBirth() == null || employee.getDateOfBirth().trim().isEmpty()) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.mandatory", "The dateOfBirth is a mandatory field");
        } else if (!employee.getDateOfBirth().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            errors.rejectValue("dateOfBirth", "dateOfBirth.invalid", "The dateOfBirth should be in YYYY-MM-DD format");
        }
    }
}

