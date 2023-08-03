'use strict';

const Generator = require('yeoman-generator');
const env = require('yeoman-environment').createEnv();

module.exports = class extends Generator {
  async prompting() {
    // Registre os diretórios dos seus templates
    await env.lookup();

    // Obtenha a lista de templates disponíveis em `env.getGeneratorsMeta()`
    const templateList = env.getGeneratorsMeta();


    // Defina o nome do gerador que você deseja filtrar
    const desiredGenerator = 'softfocus';
    
    // Filtrar os nomes dos templates para incluir apenas aqueles do gerador desejado
    const templateNames = Object.keys(templateList).filter(
      (name) => name.startsWith(desiredGenerator)
    );

     // Verifique se há opções disponíveis para o gerador desejado
     if (templateNames.length === 0) {
      this.log(`Não foram encontrados templates para o gerador: ${desiredGenerator}`);
      return;
    }

    // Defina a pergunta para o usuário escolher o template
    const templateQuestion = {
      type: 'list',
      name: 'selectedTemplate',
      message: 'Escolha um template:',
      choices: templateNames,
    };

    // Pergunte ao usuário qual template ele deseja utilizar
    const answers = await this.prompt([templateQuestion]);

    // A resposta do usuário estará disponível em `answers.selectedTemplate`
    this.log(`Você selecionou o template: ${answers.selectedTemplate}`);
    this.composeWith(answers.selectedTemplate);
  }
  
  // Código do seu gerador aqui...
};
