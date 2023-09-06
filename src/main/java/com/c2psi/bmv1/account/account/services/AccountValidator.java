package com.c2psi.bmv1.account.account.services;

import com.c2psi.bmv1.account.account.models.Account;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.article.service.ArticleService;
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

@Service(value = "AccountValidatorV1")
@Slf4j
@RequiredArgsConstructor
public class AccountValidator {
    final AppService appService;
    final ArticleService articleService;
    final PackagingService packagingService;
    final PointofsaleService posService;
    final ProviderService providerService;
    final ClientService clientService;

    public List<String> validate(AccountDto accountDto){
        List<String> errors = new ArrayList<>();
        ArticleDto articleDto = null;
        PackagingDto packagingDto = null;
        ProviderDto providerDto = null;
        ClientDto clientDto = null;
        if(accountDto == null){
            errors.add("The accountDto sent to be validate can't be null");
        }
        else{
            if(accountDto.getAccountPosId() == null){
                errors.add("The pointofsaleId of the account can't be null");
            }
            else {
                if (!posService.isPointofsaleExistWith(accountDto.getAccountPosId())) {
                    errors.add("There is no pointofsale in the systen with the posId sent");
                }
                else{
                    if (accountDto.getAccountArticleId() != null) {
                        if (!articleService.isArticleExistWith(accountDto.getAccountArticleId())) {
                            errors.add("There is no Article in the system with the articleId sent");
                        } else {
                            articleDto = articleService.getArticleById(accountDto.getAccountArticleId());
                        }
                    }

                    if (accountDto.getAccountClientId() != null) {
                        if (!clientService.isClientExistWith(accountDto.getAccountClientId())) {
                            errors.add("There is no client in the system with the clientId sent");
                        } else {
                            clientDto = clientService.getClientById(accountDto.getAccountClientId());
                        }
                    }

                    if (accountDto.getAccountPackagingId() != null) {
                        if (!packagingService.isPackagingExistWith(accountDto.getAccountPackagingId())) {
                            errors.add("There is no packaging in the system with the packagingId sent");
                        } else {
                            packagingDto = packagingService.getPackagingById(accountDto.getAccountPackagingId());
                        }
                    }

                    if(accountDto.getAccountProviderId() != null){
                        if(!providerService.isProviderExistWith(accountDto.getAccountProviderId())){
                            errors.add("There is no provider in the system with the providerId sent");
                        }
                        else{
                            providerDto = providerService.getProviderById(accountDto.getAccountProviderId());
                        }
                    }

                    if(articleDto != null){
                        if(!articleDto.getArtPosId().equals(accountDto.getAccountPosId())){
                            errors.add("The article must belong to the same pointofsale precise for the account");
                        }
                    }

                    if(clientDto != null){
                        if(!clientDto.getClientPosId().equals(accountDto.getAccountPosId())){
                           errors.add("The client must belong to the same pointofsale precise for the account");
                        }
                    }

                    if(providerDto != null){
                        if(!providerDto.getProviderPosId().equals(accountDto.getAccountPosId())){
                            errors.add("The provider must belong to the same pointofsale precise for the account");
                        }
                    }

                    if(packagingDto != null){
                        if(!packagingDto.getPackagingPosId().equals(accountDto.getAccountPosId())){
                            errors.add("the packaging must belong to the same pointofsale precise for the account");
                        }
                    }

                }
            }
        }
        return errors;
    }

    public List<String> validate(Account account){
        List<String> errors = new ArrayList<>();
        if(account == null){
            errors.add("The account to validate can't be null");
        }
        else{
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Account>> constraintViolations = validator.validate(account);

            if (constraintViolations.size() > 0) {
                for (ConstraintViolation<Account> contraintes : constraintViolations) {
                /*System.out.println(contraintes.getRootBeanClass().getSimpleName()+
                        "." + contraintes.getPropertyPath() + " " + contraintes.getMessage());*/
                    errors.add(contraintes.getMessage());
                }
            }
            errors.addAll(this.validateStringofBm(account));
        }
        return errors;
    }

    public List<String> validate(List<Filter> filterList, List<Orderby> sortCriterias){
        List<Field> accountFields = Arrays.stream(Account.class.getDeclaredFields()).toList();
        List<Field> accountInheritFields = Arrays.stream(Account.class.getSuperclass().getDeclaredFields()).toList();
        return appService.checkColumnList(filterList, sortCriterias, accountFields, accountInheritFields);
    }

    private List<String> validateStringofBm(Account account) {
        List<String> errors = new ArrayList<>();

        for(Method method : account.getClass().getDeclaredMethods()){
            if(method.isAnnotationPresent(BmNotBlank.class)){
                BmNotBlank bmNotBlank = method.getAnnotation(BmNotBlank.class);
                String message = bmNotBlank.message();
                try {
                    Object objectValueSent = method.invoke(account);
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
