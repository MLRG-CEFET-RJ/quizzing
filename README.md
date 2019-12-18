# Quizzing

Quizzing é uma aplicação de ensino que consta com uma ferramenta para publicação de questões multipla em uma base de
 dados compartilhada e a utilização dessa base para a criação de listas de exercício para serem resolvidas online.

O projeto inclui quatro source codes diferentes, sendo eles dois de backend implementados em spring boot e dois de 
frontend implementados em angular.

### Configurações

#### Com Docker

- Usando o maven faça o build das aplicações java do aluno e do professor contidas nas pastas /backend/student e /backend/teacher respectivamente;
- A partir do terminal execute o comando `docker-compose up` dentro da pasta /backend;
- Para desenvolvimento execute o comando `npm start` nas pastas /frontend/Student App e /frontend/Teacher App/quizzing para executar as aplicações web do aluno e do professor respectivamente;
- Para produção execute o comando `npm run build` nas mesmas pastas do passo anterior. Dentro de cada pasta será criada uma pasta dist que contém a aplicação pronta para ser disponibilizada em um servidor de aplicação web (Apache, nginx, etc);

### Sem Docker

- Crie um schema chamado Quizzing no mysql;
- Configure a url de acesso ao banco nos arquivos .properties das aplicações do aluno e do professor;
- Crie um usuário Quizzing com a senha Quizzing com permissão completa para esse schema (para maior segurança, o usuário e senha devem ser alterados nos arquivos .properties dos backends);
- Suba o servidor Solr com a porta padrão na máquina ou configure a url de acesso no arquivo .properties da aplicação do professor;
- Usando o maven faça o build das aplicações java do aluno e do professor contidas nas pastas /backend/student e /backend/teacher respectivamente;
- Dentro das pastas target geradas em cada aplicação execute o comando `java -Djava.security.egd=file:/dev/./urandom -jar /app-1.0.jar`
- Para desenvolvimento execute o comando `npm start` nas pastas /frontend/Student App e /frontend/Teacher App/quizzing para executar as aplicações web do aluno e do professor respectivamente;
- Para produção execute o comando `npm run build` nas mesmas pastas do passo anterior. Dentro de cada pasta será criada uma pasta dist que contém a aplicação pronta para ser disponibilizada em um servidor de aplicação web (Apache, nginx, etc);
