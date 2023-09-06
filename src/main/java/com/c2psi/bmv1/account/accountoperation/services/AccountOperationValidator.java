package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.account.account.services.AccountService;
import com.c2psi.bmv1.account.accountoperation.models.AccountOperation;
import com.c2psi.bmv1.account.operation.services.OperationService;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.AccountOperationDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service(value = "AccountOperationValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class AccountOperationValidator {
    final AppService appService;
    final AccountService accountService;
    final OperationService operationService;

    public List<String> validate(AccountOperationDto accountOperationDto){
        List<String> errors = new ArrayList<>();
        if(accountOperationDto == null){
            errors.add("The account operation Dto can't be null");
        }
        else{
            if(accountOperationDto.getOperationId() == null){
                errors.add("The operation in accountOperation can't be null");
            }
            else{
                if(!operationService.isOperationExistWith(accountOperationDto.getOperationId())){
                    errors.add("There is no operation in the system with the id sent");
                }
            }

            if(accountOperationDto.getAccountId() == null){
                errors.add("The account in accountoperation can't be null");
            }
            else{
                if(!accountService.isAccountExistWith(accountOperationDto.getAccountId())){
                    errors.add("There is no account in the system with the id sent");
                }
            }
        }
        return errors;
    }

    public List<String> validate(AccountOperation accountOperation){
        List<String> errors = new ArrayList<>();
        if(accountOperation == null){
            errors.add("The accountoperation can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<AccountOperation>> constraintViolations = validator.validate(accountOperation);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<AccountOperation> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(accountOperation));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> accountoperationFields = Arrays.stream(AccountOperation.class.getDeclaredFields()).toList();
        List<Field> accountoperationInheritFields = Arrays.stream(AccountOperation.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, accountoperationFields, accountoperationInheritFields);
    }

    private List<String> validateStringofBm(AccountOperation accountOperation) {
        List<String> errors = new ArrayList<>();

        for(Method method : accountOperation.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(accountOperation);
                    if(objectValueSent instanceof String stringValueSent){
                        /***
                         * On se rassure que la chaine saisi une fois non null n'est ni empty ni blank
                         * et ensuite que sa taille est comprise entre le min et le max defini
                         * */
                        if(appService.isBlankValueIfNotNull(stringValueSent)) {
                            errors.add(message);
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Access denied to this method due to encapsulation");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("InvocationTargetException");
                }
            }
        }
        return errors;
    }

}
