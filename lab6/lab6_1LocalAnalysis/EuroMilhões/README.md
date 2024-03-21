# Local Analysis

## Running SonarQube Server Tests

```
mvn clean verify sonar:sonar   -Dsonar.projectKey=euromilhoes   -Dsonar.projectName='euromilhoes'   -Dsonar.host.url=http://127.0.0.1:9000   -Dsonar.token=sqp_e322a0f9e94e535c78b9f4e8e55cba32615ee515
```


## Test Results

My project passed the defined quality gate, with a total of 0 bugs, 0 vulnerabilities and 1 Security Hotspot

| Issue              | Problem description                                          | How to solve                                                 |
| ------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Security Hotspot   | Dip.java the use of `new Random()` is not secured. | Make sure that using this pseudorandom number generator is safe here. |
| Code Smell (major) | Refactor the code in order to not assign to this loop counter from within the loop body. | Increment the loop counter on the definition of the for loop. |

