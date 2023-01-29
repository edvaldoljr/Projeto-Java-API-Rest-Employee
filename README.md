# API Rest - Employee

A aplicação é um sistema web desenvolvido com o framework Spring Boot, que permite o gerenciamento de funcionários de uma empresa. Ele é composto por uma camada de serviços, controladores e entidades, além de uma camada de persistência que utiliza o banco de dados PostgreSQL.

Para iniciar o desenvolvimento da aplicação, é necessário ter o ambiente de desenvolvimento configurado, incluindo o JDK (Java Development Kit) e uma IDE (Integrated Development Environment) como o Eclipse ou IntelliJ IDEA. Além disso, é preciso ter o Docker instalado na máquina, pois ele será utilizado para subir uma imagem do banco de dados PostgreSQL.

Para subir o banco de dados, é preciso baixar a imagem do PostgreSQL utilizando o comando "docker pull postgres" no terminal. Em seguida, é necessário criar um container utilizando essa imagem e configurar as variáveis de ambiente necessárias, como o nome do banco de dados, usuário e senha. Isso pode ser feito utilizando o comando "docker run --name nome-container -e POSTGRES_DB=nome-banco -e POSTGRES_USER=usuario -e POSTGRES_PASSWORD=senha -p 5432:5432 -d postgres".

Com o banco de dados configurado, é possível iniciar o desenvolvimento da aplicação. Ela é composta por uma classe principal, chamada ApiApplication, que é responsável por iniciar o Spring Boot. Além disso, há uma classe controladora, chamada EmployeeController, que é responsável por receber as requisições HTTP e chamar os métodos da classe de serviços EmployeeService. Essa classe, por sua vez, é responsável por realizar as operações de CRUD (Create, Read, Update e Delete) no banco de dados, utilizando as entidades EmployeeDTO.

A lógica de negócios do aplicativo é implementada nas classes de serviço, que são injetadas como dependências nas classes de controlador. Os controladores são responsáveis por receber as requisições HTTP e delegar as tarefas para as classes de serviço. As respostas são enviadas de volta ao cliente em formato JSON.

A aplicação também utiliza o banco de dados PostgreSQL através da configuração do arquivo application.properties, onde são definidos a url, usuario e senha do banco de dados.

Por fim, a aplicação é construida e executada através do comando "mvn clean install" e "mvn spring-boot:run" no terminal, e pode ser acessada através de uma url específica, como "http://localhost:8080/api/employee/list" para visualizar a lista de funcionários.

## Classe ApiApplication

```java
// Importação da classe SpringApplication da biblioteca Spring Boot para iniciar a aplicação.
import org.springframework.boot.SpringApplication;
// Importação da classe SpringBootApplication da biblioteca Spring Boot para configuração automática da aplicação.
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação para indicar que essa classe é a classe principal da aplicação e que ela possui configurações de automação.
@SpringBootApplication
public class ApiApplication {

// Método principal que será executado quando a aplicação for iniciada.
public static void main(String[] args) {
	// Método da classe SpringApplication para iniciar a aplicação.
	SpringApplication.run(ApiApplication.class, args);
}
}
```

A classe ApiApplication é a classe principal da aplicação, responsável por iniciar a execução do projeto. Ela possui a anotação @SpringBootApplication, que indica que é uma classe de configuração principal do Spring Boot, e automaticamente configura as configurações necessárias para que a aplicação funcione corretamente.

O método main é o método principal da classe, responsável por iniciar a aplicação. Ele chama o método estático run da classe SpringApplication, passando como parâmetros a própria classe ApiApplication e os argumentos passados pelo usuário na linha de comando. Esse método é responsável por criar um contexto do Spring, gerenciar os beans e iniciar o servidor embutido.



## Classe Entity

```java
package com.company.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

//Define a classe como uma entidade do banco de dados
@Entity
//Utiliza o lombok para gerar automaticamente os métodos getters, setters, entre outros
@Data
//Utiliza o lombok para gerar automaticamente o construtor com todos os argumentos
@AllArgsConstructor
//Utiliza o lombok para gerar automaticamente o construtor sem argumentos
@NoArgsConstructor
//Define o nome da tabela no banco de dados
@Table(name= "TB_EMPLOYEE")
public class EmployeeEntity implements Serializable {
//Define que a classe é serializável e informa a versão da serialização
private static final long attachments = 1L;

//Define a variável como uma chave primária e gera automaticamente o valor através de um sequenciador
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String name;
private Integer age; //idade
private String cpf;
private String celullar;
private String office; //cargo
private String sector; //setor
private Double wage; //salario
}
		
```

A classe EmployeeEntity representa uma entidade de funcionário no banco de dados. Ela é anotada com @Entity, indicando que é uma entidade gerenciada pelo JPA (Java Persistence API). O atributo @Table(name = "TB_EMPLOYEE") especifica o nome da tabela no banco de dados que será mapeada para esta entidade.

A classe possui vários atributos, como id, name, age, cpf, celullar, office, sector e wage, que correspondem às colunas na tabela TB_EMPLOYEE. O atributo id é anotado com @Id e @GeneratedValue(strategy = GenerationType.AUTO), indicando que é a chave primária da tabela e que o valor deve ser gerado automaticamente pelo banco de dados.

A classe também é anotada com @Data, @AllArgsConstructor e @NoArgsConstructor, que são anotações do lombok, que geram automaticamente os métodos getters, setters, construtores e outros métodos úteis. Além disso, ela implementa a interface Serializable, indicando que pode ser convertida em uma sequência de bytes para ser transmitida através de uma rede ou para ser armazenada em disco.

## Classe Repository

```java
package com.company.api.repository;

import com.company.api.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository <EmployeeEntity, Long> {
}
```

- O pacote com.company.api.repository contém a interface EmployeeRepository que estende JpaRepository.
- A interface JpaRepository fornece vários métodos genéricos para acessar o banco de dados, como save, findById, findAll, etc.
- A interface EmployeeRepository estende JpaRepository e especifica o tipo de entidade e o tipo de chave primária que serão gerenciados.
- Nesse caso, a entidade gerenciada é EmployeeEntity e a chave primária é do tipo Long.
- 
- @Repository é uma anotação do Spring que indica que essa interface é um repositório e deve ser gerenciada pelo Spring.
- Isso significa que o Spring criará uma implementação dessa interface automaticamente e a injetará onde for necessário.

## Classe DTO

```java
package com.company.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {

    private String name;
    private Integer age;
    private String cpf;
    private String celullar;
    private String office;
    private String sector;
    private Double wage; //salario
}
```

Este código cria uma classe chamada EmployeeDTO, que é um objeto de transferência de dados (ou DTO, na sigla em inglês). Ele representa um funcionário da empresa e contém informações como nome, idade, CPF, número de celular, cargo, setor e salário.

A anotação @Data do Lombok é usada para gerar automaticamente getters e setters para todas as propriedades da classe. A anotação @Builder também do Lombok, gera um construtor da classe com argumentos opcionais.

Essa classe será usada para transferir informações do banco de dados para a camada de serviço e para a camada de apresentação (por exemplo, a camada de controller), e vice-versa.



## Classe Service

```java
package com.company.api.service;

import com.company.api.dto.EmployeeDTO;
import com.company.api.entity.EmployeeEntity;
import com.company.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Esta classe é uma classe de serviço, responsável por gerenciar as regras de negócio relacionadas aos funcionários
@Service
public class EmployeeService {

    //injeção de dependencia da classe EmployeeRepository
    @Autowired
    EmployeeRepository employeeRepository;

    //Método responsável por inserir um funcionário no banco de dados
    public void insertEmployee(EmployeeDTO employee) {

        //Criação de um objeto EmployeeEntity vazio
        EmployeeEntity newEmployee = new EmployeeEntity();

        //Preenchimento do objeto EmployeeEntity com os dados do DTO
        newEmployee.setName(employee.getName());
        newEmployee.setAge(employee.getAge());
        newEmployee.setCpf(employee.getCpf());
        newEmployee.setOffice(employee.getOffice());
        newEmployee.setCelullar(employee.getCelullar());
        newEmployee.setSector(employee.getSector());
        newEmployee.setWage(employee.getWage());

        //Salvamento do objeto EmployeeEntity no banco de dados
        employeeRepository.save(newEmployee);
    }

    //Método responsável por buscar todos os funcionários do banco de dados
    public List<EmployeeDTO> searchEmployee() {

        //Criação de uma lista vazia de EmployeeDTO
        List<EmployeeDTO> listEmployee = new ArrayList<>();

        //Percorrendo todos os funcionários do banco de dados
        employeeRepository.findAll().forEach(item->{

            //Criação de um objeto EmployeeDTO para cada funcionário encontrado
            EmployeeDTO employee = EmployeeDTO
                    .builder()
                    .name(item.getName())
                    .age(item.getAge())
                    .cpf(item.getCpf())
                    .office(item.getOffice())
                    .celullar(item.getCelullar())
                    .sector(item.getSector())
                    .wage(item.getWage())
                    .build();

            //Adicionando o objeto EmployeeDTO criado à lista
            listEmployee.add(employee);
        });

        //Retornando a lista de EmployeeDTO
        return listEmployee;
    }

}

```

A classe EmployeeService é uma classe de serviço que contém os métodos para inserir e buscar funcionários. Ela é anotada com @Service, indicando que é um bean gerenciado pelo Spring e pode ser injetado em outras classes com @Autowired.

A classe tem uma dependência injetada de EmployeeRepository, que é uma interface extendida de JpaRepository, fornecendo acesso aos métodos básicos de CRUD para a entidade EmployeeEntity.

O método insertEmployee recebe um objeto EmployeeDTO como parâmetro e cria uma nova entidade EmployeeEntity para ser salva no banco de dados. Ele atribui os valores do objeto EmployeeDTO para os atributos correspondentes da entidade e chama o método save do repository para persistir os dados.

O método searchEmployee não recebe nenhum parâmetro e busca todos os funcionários do banco de dados. Ele retorna uma lista de objetos EmployeeDTO, preenchendo cada objeto com os dados correspondentes da entidade. Ele utiliza o método findAll do repository para recuperar todas as entidades e o método forEach para iterar sobre cada entidade, criando um objeto EmployeeDTO correspondente e adicionando-o à lista.



## Classe Controller

```java
package com.company.api.controller;

import com.company.api.dto.EmployeeDTO;
import com.company.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping(value = "api/employee") //Define o endpoint base para as requisições da classe
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //GET
    @GetMapping("/list") //Mapeia a requisição GET para a url "api/employee/list"
    public ResponseEntity<List<EmployeeDTO>> searchEmployeeCompany() { //Retorna uma lista de funcionários
        return new ResponseEntity<>(employeeService.searchEmployee(), HttpStatus.OK); //Retorna o HTTP status 200 (OK) junto com a lista de funcionários
    }

    @PostMapping //Mapeia a requisição POST para a url "api/employee"
    public ResponseEntity createNewEmployee(@RequestBody EmployeeDTO employee) { //Recebe os dados do funcionário no corpo da requisição
        try {
            employeeService.insertEmployee(employee); //Insere o funcionário
            return new ResponseEntity(HttpStatus.CREATED); //Retorna o HTTP status 201 (CREATED)
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //Retorna o HTTP status 500 (INTERNAL_SERVER_ERROR) em caso de erro
        }
    }

}

```

A classe EmployeeController é responsável por gerenciar as requisições HTTP relacionadas aos funcionários da empresa. Ela utiliza o EmployeeService para realizar operações como listar os funcionários e inserir novos funcionários.

Linha 8: A anotação @RestController indica que a classe é um controlador REST. Linha 9: A anotação @RequestMapping(value = "api/employee") define que todas as rotas deste controlador devem começar com "api/employee". Linha 11: A anotação @Autowired instancia automaticamente a classe EmployeeService.

Linha 14: A anotação @GetMapping("/list") define que este método deve ser chamado quando uma requisição GET for feita na rota "api/employee/list". O método searchEmployeeCompany() chama o método searchEmployee() do EmployeeService para listar todos os funcionários e retorna uma resposta HTTP com o status OK e a lista de funcionários.

Linha 18: A anotação @PostMapping define que este método deve ser chamado quando uma requisição POST for feita na rota "api/employee". O método createNewEmployee() recebe como parâmetro um objeto EmployeeDTO, que representa as informações do novo funcionário, e chama o método insertEmployee() do EmployeeService para inserir esse funcionário. Caso ocorra algum erro, uma resposta HTTP com o status INTERNAL_SERVER_ERROR é retornada. Se tudo ocorrer bem, uma resposta HTTP com o status CREATED é retornada.

## Configurando Banco de Dados.

```sql
# Configuração da conexão com o banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/company
spring.datasource.username=postgres
spring.datasource.password=1234

# Configuração do Hibernate
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Configuração do Hibernate para criação automática das tabelas no banco de dados
# As opções disponíveis são: create, create-drop, validate, update
spring.jpa.hibernate.ddl-auto=create

```

Essas são configurações do arquivo application.properties do Spring Boot. Elas especificam detalhes sobre a conexão com o banco de dados PostgreSQL e configurações do Hibernate, que é uma biblioteca de gerenciamento de persistência de objetos relacionais utilizada pelo Spring Boot.

A primeira linha especifica a URL de conexão com o banco de dados PostgreSQL, que está sendo executado localmente na porta 5432 e usando o banco de dados "company". A segunda e terceira linhas especificam o nome de usuário e senha para se conectar ao banco de dados.

A quarta linha habilita a criação de LOB (Large Object) não-contextuais no Hibernate. A quinta linha especifica o dialecto do banco de dados PostgreSQL a ser usado pelo Hibernate.

A última linha especifica o modo de geração ddl do Hibernate, que pode ser "create", "create-drop", "validate" ou "update". "create" significa que o Hibernate irá criar as tabelas no banco de dados se elas não existirem. "create-drop" significa que as tabelas serão criadas e apagadas quando a aplicação for encerrada. "validate" significa que o Hibernate irá validar as tabelas existentes no banco de dados. "update" significa que o Hibernate irá atualizar as tabelas existentes no banco de dados para corresponder às entidades gerenciadas pelo Hibernate.

## Docker

Para instalar o Docker, você pode seguir os seguintes passos:

1. Faça o download do Docker para o seu sistema operacional a partir do site oficial do Docker (https://www.docker.com/).
2. Execute o arquivo de instalação baixado e siga as instruções do assistente de instalação.
3. Verifique se o Docker está instalado corretamente executando o comando "docker --version" no terminal.
4. Agora você pode usar o Docker para baixar e executar containers de aplicativos, incluindo bancos de dados.

Para subir um banco de dados Postgres com as configurações da aplicação, você pode seguir os seguintes passos:

1. Execute o comando "docker pull postgres" no terminal para baixar a imagem do Postgres.
2. Execute o comando "docker run --name nome_do_container -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -e POSTGRES_DB=database -p 5432:5432 -d postgres" para criar e iniciar um container com o nome desejado, usuário, senha e nome do banco de dados especificado.
3. Para verificar se o container está rodando, execute o comando "docker ps" e verifique se o container com o nome especificado está na lista de containers em execução.
4. Agora você pode usar as informações de conexão, como endereço do host, porta, usuário e senha, para configurar sua aplicação para se conectar ao banco de dados.
5. Para parar o container execute o comando "docker stop nome_do_container"
6. Para remover o container execute o comando "docker rm nome_do_container"
7. Para remover a imagem execute o comando "docker rmi postgres"

Obs: é importante lembrar que esses comandos só funcionam se você possuir permissões administrativas, caso contrário você deve adicionar o prefixo "sudo" antes dos comandos.

![](https://github.com/edvaldoljr/Projeto-Java-API-Rest-Employee/blob/main/img/Espancao-java.jpg?raw=true)

Espero que você tenha gostado do aplicativo! Se você desejar, por favor dê uma estrela no repositório do GitHub.