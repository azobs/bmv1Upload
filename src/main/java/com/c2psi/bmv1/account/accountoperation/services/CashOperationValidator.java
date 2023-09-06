package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.account.accountoperation.models.CashOperation;
import com.c2psi.bmv1.account.operation.services.OperationService;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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

@Service(value = "CashOperationValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class CashOperationValidator {
    final AppService appService;
    final PointofsaleService posService;
    final ProviderService providerService;
    final ClientService clientService;
    final OperationService operationService;

    public List<String> validate(CashOperationDto cashOperationDto){
        List<String> errors = new ArrayList<>();
        ProviderDto providerDto = null;
        ClientDto clientDto = null;
        if(cashOperationDto == null){
            errors.add("The cashOperationDto can't be null");
        }
        else{
            if(cashOperationDto.getOperationId() == null){
                errors.add("The operation in cashOperation can't be null");
            }
            else{
                if(!operationService.isOperationExistWith(cashOperationDto.getOperationId())){
                    errors.add("There is no operation in the system with the id sent");
                }
            }

            if(cashOperationDto.getPosConcernedId() == null){
                errors.add("The pos is cashOperation can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(cashOperationDto.getPosConcernedId())){
                    errors.add("There is no pointofsale in the systen with the id sent");
                }
            }

            if(cashOperationDto.getClientConcernedId() != null){
                if(!clientService.isClientExistWith(cashOperationDto.getClientConcernedId())){
                    errors.add("There is no client in the system with the id sent");
                }
                else{
                    clientDto = clientService.getClientById(cashOperationDto.getClientConcernedId());
                }
            }

            if(cashOperationDto.getProviderConcernedId() != null){
                if(!providerService.isProviderExistWith(cashOperationDto.getProviderConcernedId())){
                    errors.add("There is no provider in the system with the od sent");
                }
                else {
                    providerDto = providerService.getProviderById(cashOperationDto.getProviderConcernedId());
                }
            }

            if(clientDto != null){
                if(!clientDto.getClientPosId().equals(cashOperationDto.getPosConcernedId())){
                    errors.add("The client must belong to the same pointofsale precise");
                }
            }

            if(providerDto != null){
                if(!providerDto.getProviderPosId().equals(cashOperationDto.getPosConcernedId())){
                    errors.add("The provider must belong to the same pointofsale precise");
                }
            }
        }
        return errors;
    }

    public List<String> validate(CashOperation cashOperation){
        List<String> errors = new ArrayList<>();
        if(cashOperation == null){
            errors.add("The cashoperation can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<CashOperation>> constraintViolations = validator.validate(cashOperation);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<CashOperation> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(cashOperation));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> cashoperationFields = Arrays.stream(CashOperation.class.getDeclaredFields()).toList();
        List<Field> cashoperationInheritFields = Arrays.stream(CashOperation.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, cashoperationFields, cashoperationInheritFields);
    }

    private List<String> validateStringofBm(CashOperation cashoperation) {
        List<String> errors = new ArrayList<>();

        for(Method method : cashoperation.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(cashoperation);
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
