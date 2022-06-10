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

        this.option('uri', {
            type: String,
            desc: "Uri"
        })
    }

    get initializing() {
        this.logSuccess('Generating DTO, WebClientConfig and Integrator');
        return {
            validateEntityName() {
                const context = this.context;
                console.log(`EntityName: ${this.options.entityName}, Uri: ${this.options.basePath}`);
                //this.env.error("The entity name is invalid");
            }
        }
    }

    /*get prompting() {
        return prompts.prompting;
    }*/

    configuring() {
        this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        this.configOptions.uri = this.options['uri'];
        this.configOptions.entityName = this.options.entityName;
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
        this.configOptions.security = this.configOptions.security;
        this.configOptions.rabbit = this.configOptions.rabbit;
        this.configOptions.webclient = true;
        Object.assign(configOptions, webclient);
        this.config.set(webclient);
        Object.assign(this.configOptions, constants);
    }


    writing() {
        this._generateAppCode(this.configOptions);
        this._generateMavenConfig(this.configOptions);
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
            {src: 'adapters/mapper/Entity.java', dest: 'adapters/mapper/'+configOptions.entityName+'Mapper.java'},
            {src: 'adapters/mapper/Converter.java', dest: 'adapters/mapper/Converter.java'},
            {src: 'ports/out/Port.java', dest: 'ports/out/'+configOptions.entityName+'IntegrationPort.java'},
            {src: 'ports/in/Port.java', dest: 'ports/in/'+configOptions.entityName+'ServicePort.java'},
            {src: 'adapters/config/WebClientConfiguration.java', dest: 'adapters/config/WebClientConfiguration.java'},
        ];
        this.generateMainJavaCode(configOptions, mainJavaTemplates);

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
        const mavenConfigDir = '../../common/files/maven/';
        this.fs.copyTpl(
            this.templatePath(mavenConfigDir + 'pom.xml'),
            this.destinationPath('pom.xml'),
            configOptions
        );
    }

};
