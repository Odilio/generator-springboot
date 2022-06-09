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

    }

    get initializing() {
        this.logSuccess('Generating DTO, RabbitConfig, producer and consumer');
        return {
            validateEntityName() {
                const context = this.context;
                console.log(`EntityName: ${this.options.entityName}`);
                //this.env.error("The entity name is invalid");
            }
        }
    }

    /*get prompting() {
        return prompts.prompting;
    }*/

    configuring() {
        this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        this.configOptions.entityName = this.options.entityName;
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
        this.configOptions.queueName = `queue-${this.configOptions.entityVarName}`;
        this.configOptions.rabbit = true;
        Object.assign(this.configOptions, constants);
    }


    writing() {
        this._generateMavenConfig(this.configOptions);
        this._generateAppCode(this.configOptions);
        this._generateBrokerClass(this.configOptions)
    }

    _generateMavenConfig(configOptions) {
        this._copyMavenWrapper(configOptions);
        this._generateMavenPOMXml(configOptions);
    }

    end() {
        //TODO; Disabling this temporarily to fix test failures.
        //this._formatCode(this.configOptions);
    }

    _generateAppCode(configOptions) {
        const mainJavaTemplates = [
            {src: 'adapters/dto/Entity.java', dest: 'adapters/dto/'+configOptions.entityName+'DTO.java'},
            {src: 'adapters/inbound/message/consumer/Consumer.java', dest: 'adapters/inbound/message/consumer/'+configOptions.entityName+'Consumer.java'},
            {src: 'adapters/inbound/message/producer/Producer.java', dest: 'adapters/inbound/message/producer/'+configOptions.entityName+'Producer.java'},
            {src: 'adapters/inbound/message/config/RabbitMQConfig.java', dest: 'adapters/inbound/message/config/RabbitMQConfig.java'},
        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

    }

    _generateBrokerClass(configOptions) {

        if(configOptions.brokerTool === 'consumer') {

        }

        if(configOptions.brokerTool === 'producer') {
        }
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
