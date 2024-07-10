# Order flow implementation

#### Intro

This project is dedicated to have a code snippet of the working process management engines.
Here we got simple microservice backend architecture:

- order-starter
- order-intermediate
- order-ending

#### Order flow

The whole idea of the instance of order workflow could be described as so:

1. some product is being validated (for now it is done on a engines level, as the simplest example)
    1. in case of validation error -> move to exception
    2. otherwise -> proceed
2. `order-starter` is called with product and creates order with status `STARTED`
3. wait 5 seconds
4. `order-intermediate` updates order with status `SHIPPING`
5. order should be updated with a third party side with some external message
    1. if status is not sent -> move to exception
    2. otherwise -> proceed
6. `order-ending` sets order to `CLOSED`

#### Camunda

Camunda offers bpmn schemas for providing the process flow. They are pretty straight forward and easy to work with.
You could delay, make timers and branch the logic. Camunda offers special `Manual task` that could cover step 5 from
above logic.

#### Temporal

On the contrary, Temporal doesn't have graphical representation and it sticks to the good-old codeing. Here steps are
divided into activities and the processes are workflows. The process could be updated from the UI with help
of `SignalMethod`.

#### Starting up

First of all, run all containers described under `docker-compose.yml`.
Then run `order-starter`, `order-intermidiate`, `order-ending`.
When everything successfully booted, you can run `order-camunda` and `order-temporal`.

#### Miscellaneous

Almost all services have swagger-ui accessible via:

```
http://localhost:{port}/swagger-ui/index.html
```

Camunda on the other hand has different proc-starter url:

```
POST http://localhost:8084/engine-rest/process-definition/key/OrderFlow/start
Body:
{
    "variables": {
        "productList": {
            "value": "${some-value}",
            "type": "String"
        }
    }
}
```
