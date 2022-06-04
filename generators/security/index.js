'use strict';
const BaseGenerator = require('../base-generator');
const constants = require('../constants');
const _ = require('lodash');

module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};

        this.argument("entityName", {
            type: String,
            required: true,
            description: "Entity name"
        });

        this.option('base-path', {
            type: String,
            desc: "Base URL path for REST Controller"
        })
    }

    get initializing() {
        this.logSuccess('Generating JPA entity, repository, service and controller');
        return {
            validateEntityName() {
                const context = this.context;
                console.log(`EntityName: ${this.options.entityName}, basePath: ${this.options.basePath}`);
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
        this.configOptions.entityName = this.options.entityName;
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
        this.configOptions.security = true;
        this.configOptions.tableName = _.lowerCase(this.options.entityName)+'s';
       
    }

    writing() {
        this._generateAppCode(this.configOptions);
        this._generateDbMigrationConfig(this.configOptions)
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
            {src: 'adapters/outbound/repositories/RoleRepository.java', dest: 'adapters/outbound/repositories/RoleRepository.java'},
            {src: 'adapters/outbound/repositories/UserRepository.java', dest: 'adapters/outbound/repositories/UserRepository.java'},
            {src: 'adapters/outbound/RoleRepositoryAdapter.java', dest: 'adapters/outbound/RoleRepositoryAdapter.java'},
            {src: 'adapters/outbound/UserRepositoryAdapter.java', dest: 'adapters/outbound/UserRepositoryAdapter.java'},
            {src: 'ports/out/RoleRepositoryPort.java', dest: 'ports/out/RoleRepositoryPort.java'},
            {src: 'ports/out/UserRepositoryPort.java', dest: 'ports/out/UserRepositoryPort.java'},
            {src: 'ports/in/RoleServicePort.java', dest: 'ports/in/RoleServicePort.java'},
            {src: 'ports/in/UserServicePort.java', dest: 'ports/in/UserServicePort.java'},
            {src: 'adapters/outbound/repositories/UserRepository.java', dest: 'adapters/outbound/repositories/UserRepository.java'},
            {src: 'adapters/dto/Entity.java', dest: 'adapters/dto/'+configOptions.entityName+'DTO.java'},
            {src: 'adapters/dto/JwtRequest.java', dest: 'adapters/dto/JwtRequest.java'},
            {src: 'adapters/dto/JwtResponse.java', dest: 'adapters/dto/JwtResponse.java'},
            {src: 'adapters/mapper/Entity.java', dest: 'adapters/mapper/'+configOptions.entityName+'Mapper.java'},
            {src: 'adapters/mapper/Converter.java', dest: 'adapters/mapper/Converter.java'},
            {src: 'application/services/Service.java', dest: 'application/services/'+configOptions.entityName+'Service.java'},
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
        const scriptTemplate = configOptions.supportDatabaseSequences ?
            "V1__new_table_with_seq.sql" : "V1__new_table_no_seq.sql";

        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__security.sql'),
            this.destinationPath('src/main/resources/db/migration/h2/V'+counter+'__create_security_table.sql'),
            configOptions
        );
       
        const flywayMigrantCounter = {
            [constants.KEY_FLYWAY_MIGRATION_COUNTER]: counter
        };
        this.config.set(flywayMigrantCounter);
    }
};
