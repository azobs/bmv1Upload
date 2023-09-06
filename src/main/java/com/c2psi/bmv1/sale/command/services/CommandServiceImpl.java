package com.c2psi.bmv1.sale.command.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.CommandStateEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.mappers.ClientMapper;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.loading.loading.mappers.LoadingMapper;
import com.c2psi.bmv1.loading.loading.services.LoadingService;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.command.dao.CommandDao;
import com.c2psi.bmv1.sale.command.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.command.mappers.CommandMapper;
import com.c2psi.bmv1.sale.command.models.Command;
import com.c2psi.bmv1.sale.delivery.delivery.mappers.DeliveryMapper;
import com.c2psi.bmv1.sale.delivery.delivery.services.DeliveryService;
import com.c2psi.bmv1.sale.saleinvoice.mappers.SaleinvoiceMapper;
import com.c2psi.bmv1.sale.saleinvoice.services.SaleinvoiceService;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.services.UserbmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "CommandServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommandServiceImpl implements CommandService{

    final AppService appService;
    final CommandValidator commandValidator;
    final CommandDao commandDao;
    final CommandMapper commandMapper;
    final CommandSpecService commandSpecService;
    final DeliveryService deliveryService;
    final DeliveryMapper deliveryMapper;
    final LoadingService loadingService;
    final LoadingMapper loadingMapper;
    final ClientService clientService;
    final ClientMapper clientMapper;
    final UserbmService userbmService;
    final UserbmMapper userbmMapper;
    final SaleinvoiceService saleinvoiceService;
    final SaleinvoiceMapper saleinvoiceMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;


    @Override
    public CommandDto saveCommand(CommandDto cmdDto) {
        log.info("We start saving a command by check if cmdDto is null");
        if(cmdDto == null){
            throw new NullValueException("Le cmdDto a save ne saurait etre null");
        }
        log.info("We continue saving a command by validate the cmdDto");
        List<String> errorsDto = commandValidator.validate(cmdDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("le cmdDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.COMMAND_NOT_VALID.name());
        }
        log.info("We continue saving a command by validate the cmd associate to the cmdDto");
        List<String> errors = commandValidator.validate(commandMapper.dtoToEntity(cmdDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le cmd associe au cmdDto envoye n'est pas valide ", errors,
                    ErrorCode.COMMAND_NOT_VALID.name());
        }
        log.info("We continue saving a command by ensure that the cmd will be unique");
        if(!isCommandCodeUsableInPos(cmdDto.getCmdCode(), cmdDto.getCmdPosId())){
            throw new DuplicateEntityException("Il existe deja une command dans le systeme avec ce code dans ce posId",
                    ErrorCode.COMMAND_DUPLICATED.name());
        }
        log.info("We continue saving a command by preparing the command to save");
        //Command commandToSave = commandMapper.dtoToEntity(cmdDto);
        /*************
         * commandToUpdate.setCmdCode(cmdDto.getCmdCode());
         *         commandToUpdate.setCmdDate(cmdDto.getCmdDate());
         *         commandToUpdate.setCmdComment(cmdDto.getCmdComment());
         *         commandToUpdate.setCmdState(convertToCommandStateEnum(cmdDto.getCmdState()));
         *         commandToUpdate.setCmdNature(convertToCommandNatureEnum(cmdDto.getCmdNature()));
         *         commandToUpdate.setCmdDelivery(cmdDto.getCmdDeliveryId() != null ? deliveryMapper.dtoToEntity(
         *                 deliveryService.getDeliveryById(cmdDto.getCmdDeliveryId())):null);
         *         commandToUpdate.setCmdLoading(cmdDto.getCmdLoadingId() != null ? loadingMapper.dtoToEntity(
         *                 loadingService.getLoadingById(cmdDto.getCmdLoadingId())):null);
         *         commandToUpdate.setCmdClient(clientMapper.dtoToEntity(clientService.getClientById(cmdDto.getCmdClientId())));
         *         commandToUpdate.setCmdSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(cmdDto.getCmdSalerId())));
         *         commandToUpdate.setCmdSaleinvoice(cmdDto.getCmdInvoiceId() != null ? saleinvoiceMapper.dtoToEntity(
         *                 saleinvoiceService.getSaleinvoiceById(cmdDto.getCmdInvoiceId())):null);
         *         commandToUpdate.setCmdPos(posMapper.dtoToEntity(posService.getPointofsaleById(cmdDto.getCmdPosId())));
         */
        Command commandToSave = Command.builder()
                .cmdCode(cmdDto.getCmdCode())
                .cmdDate(cmdDto.getCmdDate())
                .cmdComment(cmdDto.getCmdComment())
                .cmdState(convertToCommandStateEnum(cmdDto.getCmdState()))
                .cmdNature(convertToCommandNatureEnum(cmdDto.getCmdNature()))
                .cmdDelivery(cmdDto.getCmdDeliveryId() != null ? deliveryMapper.dtoToEntity(
                        deliveryService.getDeliveryById(cmdDto.getCmdDeliveryId())):null)
                .cmdLoading(cmdDto.getCmdLoadingId() != null ? loadingMapper.dtoToEntity(
                        loadingService.getLoadingById(cmdDto.getCmdLoadingId())):null)
                .cmdClient(clientMapper.dtoToEntity(clientService.getClientById(cmdDto.getCmdClientId())))
                .cmdSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(cmdDto.getCmdSalerId())))
                .cmdSaleinvoice(cmdDto.getCmdInvoiceId() != null ? saleinvoiceMapper.dtoToEntity(
                        saleinvoiceService.getSaleinvoiceById(cmdDto.getCmdInvoiceId())):null)
                .cmdPos(posMapper.dtoToEntity(posService.getPointofsaleById(cmdDto.getCmdPosId())))
                .build();
        return commandMapper.entityToDto(commandDao.save(commandToSave));
    }

    Boolean isCommandCodeUsableInPos(String cmdCode, Long posId){
        if(cmdCode == null || posId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Command> optionalCommand = commandDao.findCommandByCodeInPos(cmdCode, posId);
        return optionalCommand.isEmpty();
    }

    @Override
    public CommandDto updateCommand(CommandDto cmdDto) {
        log.info("We start updating a command by check if cmdDto is null");
        if(cmdDto == null){
            throw new NullValueException("The cmdDto to update can't be null");
        }
        log.info("We continue updating a command by check if cmdDtoId is null");
        if(cmdDto.getId() == null){
            throw new NullValueException("The cmdDtoId of the cmd to validate can't be null");
        }
        log.info("We continue updating a command by validate the cmdDto");
        List<String> errorsDto = commandValidator.validate(cmdDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("le cmdDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.COMMAND_NOT_VALID.name());
        }
        log.info("We continue updating a command by validate the cmd");
        List<String> errors = commandValidator.validate(commandMapper.dtoToEntity(cmdDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le cmd associe au cmdDto envoye n'est pas valide ", errors,
                    ErrorCode.COMMAND_NOT_VALID.name());
        }
        log.info("We continue updating a command by retrieve the Command to validate");
        Optional<Command> optionalCommand = commandDao.findCommandById(cmdDto.getId());
        if(!optionalCommand.isPresent()){
            throw new DuplicateEntityException("Aucun command dto n'existe dans le systeme avec l'id envoye",
                    ErrorCode.COMMAND_DUPLICATED.name());
        }
        Command commandToUpdate = optionalCommand.get();
        log.info("We continue updating a command by ensure that the command will be unique");
        boolean isCmdCodeUpdated = !cmdDto.getCmdCode().equalsIgnoreCase(commandToUpdate.getCmdCode());
        boolean isCmdPosUpdated = !cmdDto.getCmdPosId().equals(commandToUpdate.getCmdPos().getId());
        if(isCmdPosUpdated){
            throw new InvalidEntityException("On ne peut modifier le pointofsale d'une command",
                    ErrorCode.COMMAND_NOT_VALID.name());
        }
        if(isCmdCodeUpdated){
            if(!isCommandCodeUsableInPos(cmdDto.getCmdCode(), cmdDto.getCmdPosId())){
                throw new DuplicateEntityException("Il existe deja une Command dans le systeme avec le code envoye dans " +
                        "le pointofsale indique ", ErrorCode.COMMAND_DUPLICATED.name());
            }
        }
        log.info("We continue updating a command by preparing the command to Update");
        commandToUpdate.setCmdCode(cmdDto.getCmdCode());
        commandToUpdate.setCmdDate(cmdDto.getCmdDate());
        commandToUpdate.setCmdComment(cmdDto.getCmdComment());
        commandToUpdate.setCmdState(convertToCommandStateEnum(cmdDto.getCmdState()));
        commandToUpdate.setCmdNature(convertToCommandNatureEnum(cmdDto.getCmdNature()));
        commandToUpdate.setCmdDelivery(cmdDto.getCmdDeliveryId() != null ? deliveryMapper.dtoToEntity(
                deliveryService.getDeliveryById(cmdDto.getCmdDeliveryId())):null);
        commandToUpdate.setCmdLoading(cmdDto.getCmdLoadingId() != null ? loadingMapper.dtoToEntity(
                loadingService.getLoadingById(cmdDto.getCmdLoadingId())):null);
        commandToUpdate.setCmdClient(clientMapper.dtoToEntity(clientService.getClientById(cmdDto.getCmdClientId())));
        commandToUpdate.setCmdSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(cmdDto.getCmdSalerId())));
        commandToUpdate.setCmdSaleinvoice(cmdDto.getCmdInvoiceId() != null ? saleinvoiceMapper.dtoToEntity(
                saleinvoiceService.getSaleinvoiceById(cmdDto.getCmdInvoiceId())):null);
        commandToUpdate.setCmdPos(posMapper.dtoToEntity(posService.getPointofsaleById(cmdDto.getCmdPosId())));

        return commandMapper.entityToDto(commandDao.save(commandToUpdate));
    }

    CommandStateEnum convertToCommandStateEnum(CommandDto.CmdStateEnum cmdStateEnum){
        switch (cmdStateEnum){
            case INEDITING:
                return CommandStateEnum.InEditing;
            case EDITED:
                return CommandStateEnum.Edited;
            case INDELIVERY:
                return CommandStateEnum.InDelivery;
            case DELIVERY:
                return CommandStateEnum.Delivery;
            default:
                throw new EnumNonConvertibleException("Le commandState envoye n'est pas reconnu par le systeme");
        }
    }

    CommandStateEnum convertToCommandNatureEnum(CommandDto.CmdNatureEnum cmdNatureEnum){
        switch (cmdNatureEnum){
            case CASH:
                return CommandStateEnum.Cash;
            case COVER:
                return CommandStateEnum.Cover;
            case DAMAGE:
                return CommandStateEnum.Damage;
            default:
                throw new EnumNonConvertibleException("Le commandNature envoye n'est pas reconnu par le systeme");
        }
    }

    @Override
    public Boolean deleteCommandById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du command a delete ne saurait etre null");
        }
        Optional<Command> optionalCommand = commandDao.findCommandById(id);
        if(!optionalCommand.isPresent()){
            throw new ModelNotFoundException("Aucune Command n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.COMMAND_NOT_FOUND.name());
        }
        if(!isCommandDeleteable(optionalCommand.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer une Command qui est deja livre ",
                    ErrorCode.COMMAND_NOT_DELETEABLE.name());
        }
        commandDao.delete(optionalCommand.get());
        return true;
    }

    Boolean isCommandDeleteable(Command command){
        if(command == null){
            throw new NullValueException("Le command envoye ne saurait etre null");
        }
        if(command.getCmdState().equals(CommandStateEnum.Delivery)){
            return false;
        }
        return true;
    }

    @Override
    public CommandDto getCommandById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du command a delete ne saurait etre null");
        }
        Optional<Command> optionalCommand = commandDao.findCommandById(id);
        if(!optionalCommand.isPresent()){
            throw new ModelNotFoundException("Aucune Command n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.COMMAND_NOT_FOUND.name());
        }

        return commandMapper.entityToDto(optionalCommand.get());
    }

    @Override
    public List<CommandDto> getListofCommand(FilterRequest filterRequest) {

        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return commandMapper.entityToDto(commandDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return commandMapper.entityToDto(commandDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return commandMapper.entityToDto(commandDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Command> commandSpecification = commandSpecService.getCommandSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return commandMapper.entityToDto(commandDao.findAll(commandSpecification));
    }

    @Override
    public PageofCommandDto getPageofCommand(FilterRequest filterRequest) {

        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Command> commandPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            commandPage = commandDao.findAll(pageable);
            return getPageofCommandDto(commandPage);
        }
        else {
            /*************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if (filterRequest.getPage() == null) {
                pagebm.setPagenum(0);
                pagebm.setPagesize(10);
                filterRequest.setPage(pagebm);
            }

            /**************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() == null) {
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                commandPage = commandDao.findAll(pageable);
                return getPageofCommandDto(commandPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() != null) {
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                commandPage = commandDao.findAll(pageable);
                return getPageofCommandDto(commandPage);
            }

            /*********************************************************************************************
             * Si l'operateur logique permettant de lier les filtres est null et que la liste des filtres
             * contient plus d'un filtre alors il ya un probleme dans les parametres
             */
            if (filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1) {
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            /****************************************************************************************************
             * On peut ici lancer une recherche selon les filtres envoyes, les classer selon les elements de tri
             * et ensuite la page demande
             */
            Specification<Command> commandSpecification = commandSpecService.getCommandSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            commandPage = commandDao.findAll(commandSpecification, pageable);
            return getPageofCommandDto(commandPage);
        }
    }

    @Override
    public Boolean isCommandExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du command a delete ne saurait etre null");
        }
        Optional<Command> optionalCommand = commandDao.findCommandById(id);
        return optionalCommand.isPresent();
    }

    PageofCommandDto getPageofCommandDto(Page<Command> commandPage){
        PageofCommandDto pageofCommandDto = new PageofCommandDto();
        pageofCommandDto.setContent(commandMapper.entityToDto(commandPage.getContent()));
        pageofCommandDto.setCurrentPage(commandPage.getNumber());
        pageofCommandDto.setPageSize(commandPage.getSize());
        pageofCommandDto.setTotalElements(commandPage.getTotalElements());
        pageofCommandDto.setTotalPages(commandPage.getTotalPages());

        return pageofCommandDto;

    }

}
