# ApiGoldenRaspberryAward

## Versões Requeridas
* java : 8
* maven: 3.8.4

### Test

* Para executar os testes, pode-se rodar o comando abaixo:

```bash
mvn test
```

### Aplicação

* Para executar a aplicação, pode-se rodar o comando abaixo:

```bash
mvn spring-boot:run
```

## Rests Disponíveis

Oara chanmar os rests localmente, pode-se usar a porta 8080. Ex: localhost:8080/api/v1/goldenraspberryaward/44
Os rests disponíveis na aplicação são:

* Criar: `POST/api/v1/goldenraspberryaward`
* Editar (by id): `PUT/api/v1/goldenraspberryaward/1`
* Apagar (by id): `DELETE/api/v1/goldenraspberryaward/1`
* Editar Parcial (by id): `PATCH/api/v1/goldenraspberryaward/updateYear/1`
* Localizar Intervalos: `GET/api/v1/goldenraspberryaward/intervalaward`

### Detalhamento do Rest

`POST/api/v1/goldenraspberryaward`

**Body:**

```json
{
  "idMovie": 26,
  "year": 1994
}
```

`PUT/api/v1/goldenraspberryaward/1`

**Body:**

```json
{
  "idMovie": 26,
  "year": 1994
}
```

`DELETE/api/v1/goldenraspberryaward/1`

**Body:**

```
Não precisa enviar nada no body.
```

`PATCH/api/v1/goldenraspberryaward/updateYear/1`

**Body:**

```json
{
  "year": 1994
}
```

`GET/api/v1/goldenraspberryaward/intervalaward`

**Body:**

```
Não precisa enviar nada no body.
```
