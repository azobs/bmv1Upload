package com.c2psi.bmv1;

import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.auth.config.JwtService;
import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.auth.permission.services.PermissionService;
import com.c2psi.bmv1.auth.services.UserbmRolePermissionService;
import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.auth.token.services.TokenService;
import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
import com.c2psi.bmv1.bmapp.enumerations.TokenType;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.role.services.RoleService;
import com.c2psi.bmv1.userbm.models.Userbm;
import com.c2psi.bmv1.userbm.services.UserbmService;
import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import com.c2psi.bmv1.userbmrole.services.UserbmRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing //To precise that we have some classes that spring must auditing.
@Slf4j
public class Bmv1Application {

	public static void main(String[] args) {

		SpringApplication.run(Bmv1Application.class, args);
	}

//	@Bean
//	public void test(){
//		log.info("Start the bean test");
//		//String nullString = "";//The string is empty and blank also
//		String nullString = "    ";//The string is blank only
//
//		if (nullString == null) {
//			log.warn("String is null");
//		}
//		else {
//			if (!StringUtils.hasLength(nullString)) {
//				log.warn("String is empty");
//			}
//
//			if (!StringUtils.hasLength(nullString.trim())) {
//				log.warn("String is blank {}", nullString.length());
//			}
//		}
//		log.info("End the bean test");
//	}

	@Bean
	public CommandLineRunner commandLineRunner(UserbmService ubmService, RoleService roleService,
											   UserbmRoleService ubmRoleService, PermissionService permService,
											   UserbmRolePermissionService ubmRolePermService, TokenService tokenService){
		return args -> {
			///////////////////////////////////////////////////////////////////////////////////////////////////////
			RoleDto roleSaved = null;
			UserbmDto userbmSaved = null;
			UserbmRoleDto ubmRoleSaved = null;
			PermissionDto permSaved = null;
			UserbmRolePermissionDto ubmRolePermSaved = null;
			TokenDto tokenDtoSaved = null;

			var role = new RoleDto();
			role.setRoleDescription("Description du role Admin BM");
			role.setRoleName("Administrateur");
			role.setRoleEntId(null);
			role.setRolePosId(null);
			role.setRoleType(RoleDto.RoleTypeEnum.ADMINBM);
			try {
				roleSaved = roleService.getRoleByName("Administrateur", RoleTypeEnum.ADMINBM);
			} catch (Exception e) {
				roleSaved = roleService.saveRole(role);
				log.info("The first role saved is {}", roleSaved);
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			var addressDto = new AddressDto();
			addressDto.setEmail("email@gmail.com");
			addressDto.setNumtel1("647859661");
			addressDto.setNumtel2("647859661");
			addressDto.setNumtel3("647859661");
			addressDto.setCountry("Cameroon");
			addressDto.setTown("Douala");
			addressDto.setQuarter("Babenga");
			addressDto.setLocalisation("Localisation");

			var userbm = new UserbmDto();
			userbm.setUserCni("107235260");
			userbm.setUserDob(LocalDate.now());
			userbm.setUserName("UserName");
			userbm.setUserPassword("password");
			userbm.setUserRepassword("password");
			userbm.setUserPicture("picture");
			userbm.setUserState(UserbmDto.UserStateEnum.ACTIVATED);
			userbm.setUserSurname("surname");
			userbm.setUserLogin("userLogin");
			try{
				userbmSaved = ubmService.getUserbmByCni("107235260");
			}
			catch (Exception e){
				userbm.setUserAddress(addressDto);
				userbmSaved = ubmService.saveUserbm(userbm);
				log.info("The userbm saved is {}", userbmSaved);
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			var ubmRole = new UserbmRoleDto();
			if(roleSaved != null && userbmSaved != null){
				ubmRole.setRoleId(roleSaved.getId());
				ubmRole.setUserbmId(userbmSaved.getId());
				try{
					ubmRoleSaved = ubmRoleService.getUserbmRoleByUserAndRole(userbmSaved.getId(), roleSaved.getId());
				}
				catch (Exception e){
					//log.info("ubmRole = {}", ubmRole);
					ubmRoleSaved = ubmRoleService.saveUserbmRole(ubmRole);
					log.info("UserbmRole saved in the system is {}", ubmRoleSaved);
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			var perm = new PermissionDto();
			perm.setPermissionName("CREATE");
			perm.setPermissionDescription("Permission de creer une reunion");
			try{
				permSaved = permService.getPermissionByName("CREATE");
			}
			catch (Exception e){
				permSaved = permService.savePermission(perm);
				log.info("Permission found is {}", permSaved);
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			var ubmRolePerm = new UserbmRolePermissionDto();
			if(ubmRoleSaved != null && permSaved != null){
				ubmRolePerm.setPermissionId(permSaved.getId());
				ubmRolePerm.setUserbmroleId(ubmRoleSaved.getId());
				try{
					ubmRolePermSaved = ubmRolePermService.getUserbmRolePermissionByUserbmRoleAndPermission(
							ubmRoleSaved.getId(), permSaved.getId());
				}
				catch (Exception e){
					ubmRolePermSaved = ubmRolePermService.saveUserbmRolePermission(ubmRolePerm);
					log.info("Userbm Role Permission found is {}", ubmRolePermSaved);
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(ubmRoleSaved != null) {
				List<TokenDto> tokenDtoList = tokenService.findAllValidTokenListByUserbm(ubmRoleSaved.getUserbmId());
				if(tokenDtoList.isEmpty()){
					tokenDtoSaved = tokenService.saveToken(ubmRoleSaved.getUserbmId());
				}
				else {
					tokenDtoSaved = tokenDtoList.get(0);
				}

				log.warn("The token value for the default user saved is", tokenDtoSaved.getTokenValue());
				log.warn("{}", tokenDtoSaved.getTokenValue());
			}
		};

	}

}
