package com.c2psi.bmv1.client.client.services;

import com.c2psi.bmv1.address.services.AddressValidator;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.dto.ClientDto;
import com.c2psi.bmv1.dto.Filter;
import com.c2psi.bmv1.dto.Orderby;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.client.client.models.Client;
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

@Service(value = "ClientValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class ClientValidator {
    final AppService appService;
    final AddressValidator addressValidator;
    final PointofsaleService pointofsaleService;

    public List<String> validate(ClientDto clientDto){
        List<String> errors = new ArrayList<>();
        if(clientDto == null){
            errors.add("The clientDto to validate can't be null");
        }
        else{
            if(clientDto.getClientPosId() == null){
                errors.add("The clientPosId of the client can't be null");
            }
            else{
                if(!pointofsaleService.isPointofsaleExistWith(clientDto.getClientPosId())){
                    errors.add("There is no Pointofsale in DB with the id sent");
                }
            }
        }
        return errors;
    }


    public List<String> validate(Client client){
        List<String> errors = new ArrayList<>();
        if(client == null){
            errors.add("The client to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Client>> constraintViolations = validator.validate(client);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Client> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(client));

            /*************************************************
             * L'adresse du Pointofsale doit aussi etre valide
             */
            if (client.getClientAddress() == null) {
                errors.add("The address of client to validate can't be null");
            }
            else {
                errors.addAll(addressValidator.validate(client.getClientAddress()));
            }
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> clientFields = Arrays.stream(Client.class.getDeclaredFields()).toList();
        List<Field> clientInheritFields = Arrays.stream(Client.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, clientFields, clientInheritFields);
    }

    private List<String> validateStringofBm(Client client) {
        List<String> errors = new ArrayList<>();

        for(Method method : client.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(client);
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
