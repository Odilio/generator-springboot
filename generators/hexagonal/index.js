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
        this.configOptions.tableName = _.lowerCase(this.options.entityName)+'s';
        this.configOptions.supportDatabaseSequences =
            this.configOptions.databaseType === 'h2'
            || this.configOptions.databaseType === 'postgresql';
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
            {src: 'adapters/inbound/controllers/Controller.java', dest: 'adapters/inbound/controllers/'+configOptions.entityName+'Controller.java'},
            {src: 'adapters/entities/Entity.java', dest: 'adapters/entities/'+configOptions.entityName+'.java'},
            {src: 'adapters/outbound/repositories/Repository.java', dest: 'adapters/outbound/repositories/'+configOptions.entityName+'Repository.java'},
            {src: 'adapters/outbound/PersistenceAdapter.java', dest: 'adapters/outbound/'+configOptions.entityName+'PersistenceAdapter.java'},
            {src: 'adapters/dto/Entity.java', dest: 'adapters/dto/'+configOptions.entityName+'DTO.java'},
            {src: 'adapters/mapper/Entity.java', dest: 'adapters/mapper/'+configOptions.entityName+'Mapper.java'},
            {src: 'adapters/mapper/Converter.java', dest: 'adapters/mapper/Converter.java'},
            {src: 'application/services/Service.java', dest: 'application/services/'+configOptions.entityName+'Service.java'},
            {src: 'ports/in/Port.java', dest: 'ports/in/'+configOptions.entityName+'ServicePort.java'},
            {src: 'ports/out/Port.java', dest: 'ports/out/'+configOptions.entityName+'PersistencePort.java'},

        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

        const testJavaTemplates = [
            {src: 'adapters/inbound/controllers/ControllerTest.java', dest: 'adapters/inbound/controllers/'+configOptions.entityName+'ControllerTest.java'},
            {src: 'adapters/inbound/controllers/ControllerIT.java', dest: 'adapters/inbound/controllers/'+configOptions.entityName+'ControllerIT.java'},
        ];
        this.generateTestJavaCode(configOptions, testJavaTemplates);
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
            this.templatePath('app/src/main/resources/db/migration/flyway/V1__new_table_with_seq.sql'),
            this.destinationPath('src/main/resources/db/migration/h2/V'+counter+'__create_'+configOptions.tableName+'_table.sql'),
            configOptions
        );
        this.fs.copyTpl(
            this.templatePath('app/src/main/resources/db/migration/flyway/'+scriptTemplate),
            this.destinationPath('src/main/resources/db/migration/'+vendor+
                '/V'+counter+'__create_'+configOptions.tableName+'_table.sql'),
            configOptions
        );
        const flywayMigrantCounter = {
            [constants.KEY_FLYWAY_MIGRATION_COUNTER]: counter
        };
        this.config.set(flywayMigrantCounter);
    }

};
