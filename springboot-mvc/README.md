# Test Spring Rest

Tools for testing...

## Jayway JsonPath

Json path examples:
[Documentation](https://github.com/json-path/JsonPath)

Operators
---------

| Operator                  | Description                                                        |
| :------------------------ | :----------------------------------------------------------------- |
| `$`                       | The root element to query. This starts all path expressions.       |
| `@`                       | The current node being processed by a filter predicate.            |
| `*`                       | Wildcard. Available anywhere a name or numeric are required.       |
| `..`                      | Deep scan. Available anywhere a name is required.                  |
| `.<name>`                 | Dot-notated child                                                  |
| `['<name>' (, '<name>')]` | Bracket-notated child or children                                  |
| `[<number> (, <number>)]` | Array index or indexes                                             |
| `[start:end]`             | Array slice operator                                               |
| `[?(<expression>)]`       | Filter expression. Expression must evaluate to a boolean value.    |


## Wiremock

Wiremock is a tool for testing our rest clients (like postman) and take make responses with certain 
configuration.

With the next url we can intercept certain urls for no scripting a json file 
for test...
```shell
httP://localhost:8080/__admin/recorder
```

### Add wiremock dependency

```xml
<dependency>
  <groupId>org.wiremock</groupId>
  <artifactId>wiremock</artifactId>
  <version>3.5.2</version>
  <scope>test</scope>
</dependency>
```

Take a look at the next code:
> [Course code](https://github.com/springframeworkguru/tsbb2b-sfg-brewery/tree/testing-w-wiremock)
