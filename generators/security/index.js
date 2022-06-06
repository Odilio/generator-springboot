'use strict';
const BaseGenerator = require('../base-generator');
const constants = require('../constants');
const _ = require('lodash');

module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};


    }

    get initializing() {
        this.logSuccess('Generating JPA entity, repository, service and controller');
        return {
            validateEntityName() {
                const context = this.context;
                //this.env.error("The entity name is invalid");
            }
        }
    }

    /*get prompting() {
        return prompts.prompting;
    }*/

    configuring() {
        this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        this.configOptions.basePath = this.options['base-path'];
        this.configOptions.entityName = 'Auth';
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
        this.configOptions.security = true;
        this.configOptions.rabbit = false;
        this.configOptions.tableName = _.lowerCase(this.options.entityName)+'s';
        Object.assign(this.configOptions, constants);
       
    }

    writing() {
        this._generateAppCode(this.configOptions);
        this._generateDbMigrationConfig(this.configOptions)
        this._copyMavenWrapper(this.configOptions);
        this._generateMavenPOMXml(this.configOptions);
    }

    end() {
        //TODO; Disabling this temporarily to fix test failures.
        //this._formatCode(this.configOptions);
    }

    _generateAppCode(configOptions) {
        const mainJavaTemplates = [
            {src: 'adapters/security/WebSecurityConfig.java', dest: 'adapters/security/WebSecurityConfig.java'},
            {src: 'adapters/security/services/UserDetailsImpl.java', dest: 'adapters/security/services/UserDetailsImpl.java'},
            {src: 'adapters/security/services/UserDetailsServiceImpl.java', dest: 'adapters/security/services/UserDetailsServiceImpl.java'},
            {src: 'adapters/security/jwt/AuthEntryPointJwt.java', dest: 'adapters/security/jwt/AuthEntryPointJwt.java'},
            {src: 'adapters/security/jwt/AuthTokenFilter.java', dest: 'adapters/security/jwt/AuthTokenFilter.java'},
            {src: 'adapters/security/jwt/JwtTokenUtil.java', dest: 'adapters/security/jwt/JwtTokenUtil.java'},
            {src: 'adapters/security/jwt/JwtUtils.java', dest: 'adapters/security/jwt/JwtUtils.java'},

            {src: 'adapters/inbound/controllers/JwtAuthenticationController.java', dest: 'adapters/inbound/controllers/JwtAuthenticationController.java'},
          
            {src: 'adapters/entities/Entity.java', dest: 'adapters/entities/'+configOptions.entityName+'.java'},
            {src: 'adapters/entities/Role.java', dest: 'adapters/entities/Role.java'},
            {src: 'adapters/entities/ERole.java', dest: 'adapters/entities/ERole.java'},

            {src: 'adapters/outbound/UserRepositoryAdapter.java', dest: 'adapters/outbound/'+configOptions.entityName+'RepositoryAdapter.java'},
            {src: 'adapters/outbound/repositories/UserRepository.java', dest: 'adapters/outbound/repositories/'+configOptions.entityName+'Repository.java'},

            {src: 'application/services/UserService.java', dest: 'application/services/'+configOptions.entityName+'Service.java'},

            {src: 'ports/out/RepositoryPort.java', dest: 'ports/out/'+configOptions.entityName+'RepositoryPort.java'},
            {src: 'ports/in/ServicePort.java', dest: 'ports/in/'+configOptions.entityName+'ServicePort.java'},
           
            {src: 'adapters/dto/Entity.java', dest: 'adapters/dto/'+configOptions.entityName+'DTO.java'},
            {src: 'adapters/dto/JwtRequest.java', dest: 'adapters/dto/JwtRequest.java'},
            {src: 'adapters/dto/JwtResponse.java', dest: 'adapters/dto/JwtResponse.java'},
            {src: 'adapters/mapper/Entity.java', dest: 'adapters/mapper/'+configOptions.entityName+'Mapper.java'},
            {src: 'adapters/mapper/Converter.java', dest: 'adapters/mapper/Converter.java'},
        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

    }

    _generateDbMigrationConfig(configOptions) {

        if(configOptions.dbMigrationTool === 'flywaydb') {
            this._generateFlywayMigration(configOptions)
        }
    }

    _generateFlywayMigration(configOptions) {
        const counter = configOptions[constants.KEY_FLYWAY_MIGRATION_COUNTER] + 1;
        let vendor = configOptions.databaseType;
        if(vendor === "mariadb") {
            vendor = "mysql";
        }

        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__security.sql'),
            this.destinationPath('src/main/resources/db/migration/h2/V2__create_security_table.sql'),
            configOptions
        );

       
        const flywayMigrantCounter = {
            [constants.KEY_FLYWAY_MIGRATION_COUNTER]: counter
        };
        this.config.set(flywayMigrantCounter);
    }

    _copyMavenWrapper(configOptions) {
        const commonMavenConfigDir = '../../common/files/maven/';

        ['mvnw', 'mvnw.cmd'].forEach(tmpl => {
            this.fs.copyTpl(
                this.templatePath(commonMavenConfigDir + tmpl),
                this.destinationPath(tmpl)
            );
        });

        this.fs.copyTpl(
            this.templatePath(commonMavenConfigDir + 'gitignore'),
            this.destinationPath('.gitignore')
        );

        this.fs.copy(
            this.templatePath(commonMavenConfigDir + '.mvn'),
            this.destinationPath('.mvn')
        );

    }

    _generateMavenPOMXml(configOptions) {
        const mavenConfigDir = 'maven/';
        this.fs.copyTpl(
            this.templatePath(mavenConfigDir + 'pom.xml'),
            this.destinationPath('pom.xml'),
            configOptions
        );
    }

};
