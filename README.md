<img src="https://github.com/ArturWood/games-list/assets/111249818/434c56b3-9dc9-412a-91f7-2edc3f389c14" width=300px alt="Java Logo" />
<img src="https://github.com/ArturWood/games-list/assets/111249818/d8539fd2-938e-4126-b3d4-7236a1ffdbef" width=500px alt="SpringFramework Logo" />

# AdoPet - Aplicação Java para realizar gerenciamento de abrigos de animais, cadastro de pets e tutores

### Projeto back-end desenvolvido utilizando Java 17 com Spring Framework

API REST responsavel por realizar operações de buscas e escritas em banco de dados MySQL e retornar os dados em JSON.

![MySql](https://github.com/ArturWood/api-adopet/assets/111249818/1258cd64-3df1-45fd-a72c-bc5f8f452dc1)

Fornece endpoints para listar, cadastrar, detalhar e realizar operações específicas em relação a abrigos, adoções, pets e tutores.

![Postman](https://github.com/ArturWood/api-adopet/assets/111249818/ac26e391-8756-4759-afb6-965c2ca5209e)

## Pré-requisitos

- Java Development Kit (JDK) versão 17
- IDE Java (como Eclipse ou IntelliJ) ou um editor de texto para escrever o código
- Fazer download das dependências e plugins utilizando maven
- MySQL instalado localmente ou container Docker com MySQL
- Postman (opcional, para testar os endpoints localmente)

## Como executar o projeto

### Back-end

```bash
# clonar repositório
git clone https://github.com/ArturWood/api-adopet.git

# entrar na pasta do projeto
cd api-adopet

# executar o projeto
./mvnw spring-boot:run
```

## Endpoints

A aplicação expõe os seguintes endpoints:

- AbrigoController `/abrigos`
  - `GET`: Retorna uma lista paginada de abrigos.
  - `POST`: Cadastra um novo abrigo com as informações fornecidos no corpo da solicitação.
  - `GET /{id}`: Retorna detalhes de um abrigo específico.
  - `GET /{id}/pets`: Retorna uma lista paginada de pets pertencentes a um abrigo específico.
  - `POST /{id}/pets`: Cadastra um novo pet em um abrigo específico com as informações fornecidos no corpo da solicitação.

- AdocaoController `/adocoes`
  - `POST`: Solicita uma adoção.
  - `GET /{id}`: Retorna detalhes de uma adoção específica.
  - `PUT /aprovar`: Aprova uma solicitação de adoção.
  - `PUT /reprovar`: Reprova uma solicitação de adoção.

- PetController `/pets`
  - `GET`: Retorna uma lista paginada de pets.
  - `GET /{id}`: Retorna detalhes de um pet específico.

- TutorController `/tutores`
  - `POST`: Cadastra um novo tutor com as informações fornecidos no corpo da solicitação.
  - `PUT`: Atualiza informações de um tutor com as informações fornecidos no corpo da solicitação.
  - `GET /{id}`: Retorna detalhes de um tutor específico.

## Estrutura do Projeto

O projeto possui a seguinte estrutura de arquivos:

```bash
├───src                                          
│   ├───main                                     
│   │   ├───java                                 
│   │   │   └───com                              
│   │   │       └───dev
│   │   │           └───api.adopet
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───model
│   │   │               ├───infra
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
└── .gitignore
└── api-adopet.postman_collection.json
└── pom.xml
```

- O pacote `resources` contém o arquivo `application.properties` que configura o ambiente da aplicação, e a conexão no banco de dados.
- O pacote `controller` contém as classes que definem os endpoints da API.
- O pacote `service` contém as classes responsáveis por acesso ao BD e chamar a lógica do negócio.
- O pacote `infra` contém a classe `ExceptionEntityHandler` responsavel por lidar com as exceptions lançadas pelo controller ou service.
- O pacote `model` contém as classes que representam os objetos e seu mapeamento no BD, assim como as validações para aplicar as regras de negócio.
- O pacote `repository` contém as interfaces que definem operações de acesso a dados para as entidades.
- Na source do projeto temos o arquivo `.gitignore` que especifica os arquivos e pastas que devem ser ignorados pelo controle de versão do Git
- O arquivo `api-adopet.postman_collection` para consultar e testar os endpoints na API.
- O arquivo `pom.xml` para download das dependencias necessarias para o projeto usando maven.

## Documentação

No projeto foi utilizado uma imagem MySQL em um Docker container para desenvolvimento;<br>
Foi adicionado a dependência `springdoc` para facilitar a documentação e visualização dos endpoints (acessar rodando localmente);<br>
Alem das dependencias para desenvolvimento com Spring Framework - Web, Bean, JPA;<br>
Links para uso e documentação:

https://hub.docker.com/_/mysql<br>
https://dev.mysql.com/doc/<br>
https://spring.io/projects/spring-data-jpa<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/web.html<br>
http://localhost:8080/swagger-ui/index.html

![swagger](https://github.com/ArturWood/api-adopet/assets/111249818/97551e10-8884-418d-ae0e-0473a4852481)
