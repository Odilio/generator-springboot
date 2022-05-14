'use strict';
const BaseGenerator = require('../base-generator');
const _ = require('lodash');

module.exports = class extends BaseGenerator {

    constructor(args, opts) {
        super(args, opts);
        this.configOptions = this.options.configOptions || {};


        this.option('image-name', {
            type: String,
            desc: "Image name"
        })
    }

    get initializing() {
        this.logSuccess('Generating k8s folder and skaffold file');
        return {
            validateEntityName() {
                const context = this.context;
                console.log(`EntityName: ${this.options.entityName}, ImageName: ${this.options.imageName}`);
                //this.env.error("The entity name is invalid");
            }
        }
    }

    /*get prompting() {
        return prompts.prompting;
    }*/

    configuring() {
        this.configOptions = Object.assign({}, this.configOptions, this.config.getAll());
        this.configOptions.imageName = this.options['image-name'];
        this.configOptions.entityName = this.options.entityName;
        this.configOptions.entityVarName = _.camelCase(this.options.entityName);
    }


    writing() {
        this._generateKube(this.configOptions);
    }

    end() {
        //TODO; Disabling this temporarily to fix test failures.
        //this._formatCode(this.configOptions);
    }

    _generateKube(configOptions) {
      
        this.fs.copyTpl(this.templatePath('app/skaffold.yaml'), this.destinationPath('skaffold.yaml'), configOptions);
        this.fs.copyTpl(this.templatePath('k8s/deployment.yaml'), this.destinationPath('k8s/deployment.yaml'), configOptions);
        this.fs.copyTpl(this.templatePath('k8s/service.yaml'), this.destinationPath('k8s/service.yaml'), configOptions);

    }
   


};
