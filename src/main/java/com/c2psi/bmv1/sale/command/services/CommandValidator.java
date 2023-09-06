package com.c2psi.bmv1.sale.command.services;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.command.models.Command;
import com.c2psi.bmv1.sale.delivery.delivery.services.DeliveryService;
import com.c2psi.bmv1.sale.saleinvoice.services.SaleinvoiceService;
import com.c2psi.bmv1.userbm.services.UserbmService;
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

@Service(value = "CommandValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class CommandValidator {
    final AppService appService;
    final DeliveryService deliveryService;
    final LoadingService loadingService;
    final ClientService clientService;
    final UserbmService userbmService;
    final SaleinvoiceService saleinvoiceService;
    final PointofsaleService posService;

    public List<String> validate(CommandDto cmdDto){
        List<String> errors = new ArrayList<>();
        DeliveryDto deliveryDto = null;
        LoadingDto loadingDto = null;
        ClientDto clientDto = null;
        SaleinvoiceDto saleinvoiceDto = null;
        boolean posExist = false;
        if(cmdDto == null){
            errors.add("The commandDto can't be null");
        }
        else{
            if(cmdDto.getCmdPosId() != null){
                if(!deliveryService.isDeliveryExistWith(cmdDto.getCmdDeliveryId())){
                    errors.add("There is no command in the DB with the commandId sent");
                }
                else {
                   deliveryDto = deliveryService.getDeliveryById(cmdDto.getCmdPosId());
                }
            }

            if(cmdDto.getCmdLoadingId() != null){
                if(!loadingService.isLoadingExistWith(cmdDto.getCmdLoadingId())){
                    errors.add("There is no loading in the DB with the loadingId sent");
                }
                else{
                   loadingDto = loadingService.getLoadingById(cmdDto.getCmdLoadingId());
                }
            }

            if(cmdDto.getCmdClientId() == null){
                errors.add("The clientId of the command can't be null");
            }
            else {
                if(!clientService.isClientExistWith(cmdDto.getCmdClientId())){
                    errors.add("There is no client in the DB with the clientId sent");
                }
                else{
                    clientDto = clientService.getClientById(cmdDto.getCmdClientId());
                }
            }

            if(cmdDto.getCmdSalerId() == null){
                errors.add("The userbm salerId of the command can't be null");
            }
            else{
                if(!userbmService.isUserbmExistWithId(cmdDto.getCmdSalerId())){
                    errors.add("There is no Userbm in the DB with the userbmId sent");
                }
            }

            if(cmdDto.getCmdInvoiceId() != null){
                if(!saleinvoiceService.isSaleinvoiceExistWith(cmdDto.getCmdInvoiceId())){
                    errors.add("There is no saleinvoice in the DB with the saleinvoiceId sent");
                }
                else {
                    saleinvoiceDto = saleinvoiceService.getSaleinvoiceById(cmdDto.getCmdInvoiceId());
                }
            }

            if(cmdDto.getCmdPosId() == null){
                errors.add("The posId of the command can't be null");
            }
            else{
                if(!posService.isPointofsaleExistWith(cmdDto.getCmdPosId())){
                    errors.add("There is no Pointofsale in the DB with the posId sent");
                }
                else{
                    posExist = true;
                }
            }

            if(posExist){
                if(cmdDto != null){
                    if(!deliveryDto.getDeliveryPosId().equals(cmdDto.getCmdPosId())){
                        errors.add("The command precise must belong to the pointofsale indicated");
                    }
                }
                if(loadingDto != null){
                    if(!loadingDto.getLoadPosId().equals(cmdDto.getCmdPosId())){
                        errors.add("The loading precise must belong to the pointofsale indicated");
                    }
                }
                if(clientDto != null){
                    if(!clientDto.getClientPosId().equals(cmdDto.getCmdPosId())){
                        errors.add("The client precise must belong to the pointofsale indicated");
                    }
                }
                if(saleinvoiceDto != null){
                    if(!saleinvoiceDto.getSiPosId().equals(cmdDto.getCmdPosId())){
                        errors.add("The saleinvoice precise must belong to the pointofsale indicated");
                    }
                }
            }

            if(cmdDto.getCmdState() != null){
                if(!isCommandStateValid(cmdDto.getCmdState())){
                    errors.add("The commandState sent is not recognized as a commandState by the system");
                }
            }

            if(cmdDto.getCmdNature() != null){
                if(!isCommandNatureValid(cmdDto.getCmdNature())){
                    errors.add("The commandNature sent is not recognized as commandNature by the system");
                }
            }
        }
        return errors;
    }

    Boolean isCommandStateValid(CommandDto.CmdStateEnum cmdStateEnum){
        switch (cmdStateEnum){
            case INEDITING:
                return true;
            case EDITED:
                return true;
            case INDELIVERY:
                return true;
            case DELIVERY:
                return true;
            default:
                return false;
        }
    }

    Boolean isCommandNatureValid(CommandDto.CmdNatureEnum cmdNatureEnum){
        switch (cmdNatureEnum){
            case CASH:
                return true;
            case COVER:
                return true;
            case DAMAGE:
                return true;
            default:
                return false;
        }
    }

    public List<String> validate(Command command){
        List<String> errors = new ArrayList<>();
        if(command == null){
            errors.add("The command sent can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Command>> constraintViolations = validator.validate(command);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Command> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(command));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> commandFields = Arrays.stream(Command.class.getDeclaredFields()).toList();
        List<Field> commandInheritFields = Arrays.stream(Command.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, commandFields, commandInheritFields);
    }

    private List<String> validateStringofBm(Command cmd) {
        List<String> errors = new ArrayList<>();

        for(Method method : cmd.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(cmd);
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
