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
    	Employee emp=(Employee) employeeObject;
    	
    	if(emp.getFullName()==null || emp.getFullName().trim().isEmpty()) {
    		errors.rejectValue("fullName", "","The fullName is a mandatory field");
    	}
    	if(emp.getMobileNumber()==null) {
    		errors.rejectValue("mobileNumber", "", "The mobileNumber is a mandatory field");
    	}else {
    		String mb=emp.getMobileNumber().toString();
    		if(!mb.matches("\\d{10}")) {
    			errors.rejectValue("mobileNumber","","The mobileNumber is a mandatory field");
    		}
    	}
    	
    	if(emp.getEmailId()==null || emp.getEmailId().trim().isEmpty()) {
    		errors.rejectValue("emailId", "","The emailId is a mandatory field");
    	}else {
    		if(!emp.getEmailId().contains("@")) {
    			errors.rejectValue("emailId","", "The emailId should be in a valid email format");
    		}
    	}
    	
    	if(emp.getDateOfBirth()==null || emp.getDateOfBirth().trim().isEmpty()) {
    		errors.rejectValue("dateOfBirth", "", "The dateOfBirth is a mandatory field");
    	}else {
    		if(!emp.getDateOfBirth().matches("^\\d{4}-d{2}-d{2}$")) {
    			errors.rejectValue("dateOfBirth", "","The dateOfBirth should be in YYYY-MM-DD format");
    		}
    	}
    }


}
 
