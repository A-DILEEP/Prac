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
 
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
 
public class EmployeeValidator implements Validator {
 
    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
//        Employee emp = (Employee) employeeObject;
//
//        // 1. fullName validation
//        if (emp.getFullName() == null || emp.getFullName().trim().isEmpty()) {
//            errors.rejectValue("fullName", "fullName.empty", "The fullName is a mandatory field");
//        }
//
//        // 2. mobileNumber validation
//        if (emp.getMobileNumber() == null || String.valueOf(emp.getMobileNumber()).length() != 10) {
//            errors.rejectValue("mobileNumber", "mobileNumber.invalid", "The mobileNumber is a mandatory field");
//        }
//
//        // 3. emailId validation
//        if (emp.getEmailId() == null || emp.getEmailId().trim().isEmpty()) {
//            errors.rejectValue("emailId", "emailId.empty", "The emailId is a mandatory field");
//        } else if (!emp.getEmailId().contains("@")) {
//            errors.rejectValue("emailId", "emailId.format", "The emailId should be in a valid email format");
//        }
//
//        // 4. dateOfBirth validation
//        if (emp.getDateOfBirth() == null || emp.getDateOfBirth().trim().isEmpty()) {
//            errors.rejectValue("dateOfBirth", "dateOfBirth.empty", "The dateOfBirth is a mandatory field");
//        } else {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate.parse(emp.getDateOfBirth(), formatter);
//            } catch (DateTimeParseException e) {
//                errors.rejectValue("dateOfBirth", "dateOfBirth.format", "The dateOfBirth should be in YYYY-MM-DD format");
//            }
//        }
    }


}
 
